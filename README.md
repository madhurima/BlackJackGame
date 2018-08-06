# BlackJackGame

Black jack, also known as twenty-one, is the most popular casino banking game in the world. It is a comparing card game between a player and dealer but not against other players. The game follows a pattern of beating the dealer by getting 21 points or by waiting till the dealer exceeds 21 or by reaching the final score without exceeding 21. We have improvised this basic idea, and developed a graphical game adding multiplayer functionality which is more secure and robust. This game follows the concept of adding all the players into the database through user login or registration pages connected to the database, that will authenticate the player to start the game. The player has four options such as “hit”, “stand”, “double”, “split” after the dealer starts the game. The dealer receives first card faced up and the second card as closed card which only dealer can see. The players has both the card faced up, and can add as many number of the cards as the player want until it bursts (>21). The win/loss of each player is updated into the database in the form of records, which can be viewed by each player when he accesses the game.



The game was developed using Spring MVC architecture and Maven as the build automation tool. We used JSP pages, JavaScript on the frontend, Java as our programming language for business logic, hibernate for interacting with MySQL database system. The server we used to deploy our application is Apache Tomcat version 7.0.
To implement the registration and login modules, we used Spring Security framework, Hibernate for database connectivity, XML configuration and build tool as Maven.
Spring MVC framework is an application framework which provides model-view-controller architecture and ready components that can be used to develop flexible web applications. It contains many modules and they are designed around DispatcherServlet which handles all the requests around servlets. Spring MVC framework supports Spring Security framework and Spring Data JPA. Spring Security focuses on providing both authentication and authorization to java applications. It also contains CRSF token implementation which is used for implementing security.
Hibernate provides object/relational mapping(ORM) that is concerned with data persistence. Maven is a build automation tool used to store jar files. Hibernate's primary feature is a mapping from Java classes to database tables, and mapping from Java data types to SQL data types. Hibernate also provides data query and retrieval facilities. It generates SQL calls and relieves the developer from the manual handling and object conversion of the result set.
Maven is used for building our application. All required jar files are defined as dependencies in pom.xml file and this ensures that all required jar files are incorporated in the system.




The components of our project are:
1. Web pages: Used HTML and CSS files
2. Spring MVC: Application framework which provides model-view-controller architecture for building web application.
3. JSP: Used as a server side scripting language used to create dynamically interactive web pages and to store and retrieve information from the database.
4. Java Programs: Used java programs to code the basic functionality of the game.
5. Database MySQL: Used to store the data.
6. Spring Hibernate: Handled requests around the servlets and ORM that is concerned with data persistence.
7. Maven: Automation build tool for our application.
8. Apache Tomcat 7.0: Server
