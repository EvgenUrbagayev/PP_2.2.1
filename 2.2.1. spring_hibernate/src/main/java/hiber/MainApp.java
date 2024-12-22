package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        Car bmw = new Car("M5", 777);
        Car mersedes = new Car("S", 999);
        Car porshe = new Car("911", 888);
        Car toyota = new Car("LC200", 666);

        carService.add(bmw);
        carService.add(mersedes);
        carService.add(porshe);
        carService.add(toyota);


        userService.add(new User("User1", "Lastname1", "user1@mail.ru", bmw));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", mersedes));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", porshe));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru",toyota));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }


        System.out.println(userService.getUserCar("911", 888));

        context.close();
    }
}
