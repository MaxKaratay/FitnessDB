# FitnessDB
Web application for fitness club.
Developed with Spring Boot, Spring MVC, Spring Security, Spring Data JPA and Hibernate, also was used Freemarker for HTML templates.
Application provide access to fitness club`s DB for different type of users (clients, instructors, administrators) each with their own privileges.
## Usage
You can find initial DB data in src\main\resources\sql\_data_.sql . 
For login form : 
 - Password its three uppercase first latter of user`s first_name.
 - Username its combination of user`s first_name + last_name + patronymic separated with spaces.
