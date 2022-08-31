package view;

import config.Config;
import controller.UserController;
import dto.request.SignInDTO;
import dto.request.SignUpDTO;
import dto.response.ResponseMessenger;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewMainMenu {

    UserController userController = new UserController();

    List<User> userList = userController.getUserList();

    public void formLogin() {
        //username
        String username;
        while (true) {
            System.out.println("Enter username:");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid username, try again!");
            }
        }
        //password
        String password;
        while (true) {
            System.out.println("Enter password:");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid password, try again!");
            }
        }
        SignInDTO signInDTO = new SignInDTO(username, password);

        ResponseMessenger responseMessenger = userController.login(signInDTO);

        switch (responseMessenger.getMessage()) {
            case "blocked":
                System.err.println("This user is blocked");
                break;
            case "login_success":
                System.out.println("Login successful!");
                new ViewMyProfile().menuLogin();
                break;
            case "login_failure":
                System.out.println("Username or password is incorrect!");
        }
    }

    public void formRegister() {
        System.out.println("*****REGISTER*****");
        //id
        int id = userController.getLastId();
        //name
        String name;
        while (true) {
            System.out.println("Enter name:");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-zA-Z0-9 ]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid name, try again!");
            }
        }
        //username
        String username;
        while (true) {
            System.out.println("Enter username:");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid username, try again!");
            }
        }
        //email
        String email;
        while (true) {
            System.out.println("Enter email:");
            email = Config.scanner().nextLine();
            if (email.matches(".+@.+")) {
                break;
            } else {
                System.out.println("Invalid email, try again!");
            }
        }
        //password
        String password;
        while (true) {
            System.out.println("Enter password:");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid password, try again!");
            }
        }
        //role
        String role = "user";
        Set<String> strRole = new HashSet<>();
        strRole.add(role);

        SignUpDTO signUpDTO = new SignUpDTO(id, name, username, email, password, strRole);


        ResponseMessenger responseMessenger = userController.register(signUpDTO);

        switch (responseMessenger.getMessage()) {
            case "user_existed":
                System.out.println("Username already exists");
                break;
            case "email_existed":
                System.out.println("Email already exists");
                break;
            case "invalid_role":
                System.out.println("Invalid role, try again!");
                break;
            case "success":
                System.out.println("Register success");
        }

    }

    private void formShowListUser() {
        System.out.printf("%-15s %s %n", "Username", "Role");
        for (User user : userList) {
            System.out.printf("%-15s %s %n", user.getUsername(), new ArrayList<>(user.getRoles()).get(0).getRoleName());
        }
    }

}
