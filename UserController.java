package org.blackjack.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.blackjack.model.Game;
import org.blackjack.model.Round;
import org.blackjack.model.User;
import org.blackjack.service.BlackJackService;
import org.blackjack.service.GameService;
import org.blackjack.service.MailService;
import org.blackjack.service.SecurityService;
import org.blackjack.service.UserService;
import org.blackjack.validator.ResetPasswordValidator;
import org.blackjack.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ResetPasswordValidator resetPasswordValidator;
    
	@Autowired
	private BlackJackService blackJackService;

	@Autowired
	private GameService gameService;
	
	@Autowired
	private MailService	mailService;
	
	private final Round round = Round.getInstance();    

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public ModelAndView welcome(Model model) {
		blackJackService.initializeTable(round);
		String username = securityService.findLoggedInUsername();
		round.setUserGames(gameService.findByUsername(username));
		String table = "<table id=\"header-fixed\"><thead><tr><th>Player Credits</th><th>Player Bet</th><th>Result</th><th>Split</th><th>Date Played</th></tr></thead><tbody>";
		
		for (Game g:round.getUserGames()){
			table = table + "<tr><td>" + g.getPlayerCredits() + "</td>"
					+ "<td>" + g.getPlayerBet() + "</td>"
					+ "<td>" + g.getResult() + "</td>"
					+ "<td>" + g.getSplithand() + "</td>"
					+ "<td>" + g.getCreateddate() + "</td></tr>";
 		}
		table = table + "</tbody></table>";
		round.setGameStatistics(table);
		round.setUsername(username);
		return new ModelAndView("welcome", "round", round);

    }
    
	@RequestMapping(value="/forgotPassword")
	public ModelAndView forgotPassword()
	{
		return new ModelAndView("forgotPassword"); 
	}
	
	@RequestMapping(value="/resetPassword" , method=RequestMethod.POST)
	public ModelAndView resetRequest(@RequestParam(value="email") String email)
	{
	    Pattern pattern;  
	    Matcher matcher;  
	    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
	    		   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  

        if (!(email != null && email.equals(""))) {  
        	pattern = Pattern.compile(EMAIL_PATTERN);  
        	matcher = pattern.matcher(email);  
        	if (!matcher.matches()) {  
        		return new ModelAndView("forgotPassword", "error", "Please enter a valid email id.");
        	}  
        }  

		
		
		//check if the email id is valid and registered with us.

		User user = userService.findByEmail(email);
		
		if (user!=null){
			mailService.sendMail(email, user.getEmailhash(), user.getUsername());
			return new ModelAndView("checkMail");
		} else {
			return new ModelAndView("login", "error", "No User found with " + email);
		}
	}
	
	@RequestMapping(value="/newPassword/{emailhash}", method = RequestMethod.GET)
	public String resetPassword(@PathVariable String emailhash,Model model)
	{
		
		User user = userService.findByEmailhash(emailhash);
		model.addAttribute("emailid", user.getEmail());
		user.setPassword("");
		model.addAttribute("userForm", user);
		return "changePassword";
	}

    @RequestMapping(value = "/newPassword/{emailhash}", method = RequestMethod.POST)
    public String newPassword(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
    	resetPasswordValidator.validate(userForm, bindingResult);
    	User user = userService.findByEmailhash(userForm.getEmailhash());
    	userForm.setUsername(user.getUsername());
    	userForm.setEmail(user.getEmail());
    	userForm.setRoles(user.getRoles());
    	userForm.setId(user.getId());
    	
        if (bindingResult.hasErrors()) {
        	model.addAttribute("userForm", userForm);
            return "changePassword";
        }

        userService.update(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
    

}
