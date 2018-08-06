package org.blackjack.service;

import org.blackjack.model.Role;
import org.blackjack.model.User;
import org.blackjack.repository.RoleRepository;
import org.blackjack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        String token = UUID.randomUUID().toString();
        user.setEmailhash(token);
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void update(User user) {
		User existingRecord = userRepository.findByEmailhash(user.getEmailhash());
		userRepository.delete(existingRecord);
        user.setUsername(existingRecord.getUsername());
        user.setEmail(existingRecord.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
	}

	@Override
	public User findByEmailhash(String emailhash) {
		return userRepository.findByEmailhash(emailhash);
	}
}
