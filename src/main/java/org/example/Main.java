package org.example;

import org.example.config.AppConfig;
import org.example.model.Car;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "user1", "user1@gmail.ru");
        Car car1 = new Car("Toyota", 2015);
        user1.setCar(car1);
        car1.setUser(user1);

        User user2 = new User("User2", "user1", "454@gmailcom");
        Car car2 = new Car("Honda", 2011);
        user2.setCar(car2);
        car2.setUser(user2);


        userService.add(user1);
        userService.add(user2);

        List<User> users = userService.getUsersByCarModelAndSeries("Toyota", 2015);
        for (User user : users) {
            System.out.println("Name: " + user.getName() + "Last name: " + user.getLastName() + ", Email: " + user.getEmail());
        }

        context.close();
    }
}
