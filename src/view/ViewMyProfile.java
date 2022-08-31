package view;

import config.Config;
import controller.UserController;
import dto.response.ResponseMessenger;
import model.RoleName;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ViewMyProfile {

    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();

    public void menuLogin() {
        RoleName roleName = new ArrayList<>(currentUser.getRoles()).get(0).getRoleName();
        switch (roleName) {
            case ADMIN:
                menuAdmin();
                break;
            case USER:
                menuUser();
                break;
            case PM:
                menuPm();
        }
    }

    public void menuLogOut() {

        System.out.println("*****MENU*****");
        System.out.println("1. Register");
        System.out.println("2 .Login");
        System.out.println("3. Country Cigarette");
        System.out.println("4. Company Cigarette");
        System.out.println("5. Show user list");

        int choice = Config.getValidInteger();

        switch (choice) {
            case 1:
                new ViewMainMenu().formRegister();
                break;
            case 2:
                new ViewMainMenu().formLogin();
                break;
            case 3:
                new ViewCountry().menu();
                break;
            case 4:
                new ViewCompany().menu();
                break;
            case 5:
                formShowListUser();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menuLogOut();
    }

    private void menuPm() {
        System.out.println("Menu");
        System.out.println("1. Country Cigarette");
        System.out.println("2. Company Cigarette");
        System.out.println("3. Manager Cigarette");
        System.out.println("4. User Manage");
        System.out.println("5. Change Password");
        System.out.println("6. Log out");

        int choice = Config.getValidInteger();
        if (choice == 0) return;
        switch (choice) {
            case 1:
                new ViewCountry().menu();
                break;
            case 2:
                new ViewCompany().menu();
                break;
            case 3:
                new ViewCigarette().menu();
                break;
            case 4:
                formUserManage();
                break;
            case 5:
                formChangePassword();
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menuPm();
    }


    public void menuUser() {
        System.out.println("Hello USER: " + currentUser.getName());
        System.out.println("3. Change password");
        System.out.println("4. Log out");

        int choice = Config.getValidInteger();

        switch (choice) {
            case 3:
                formChangePassword();
                break;
            case 4:
                logout();
                return;
        }
        menuUser();
    }

    private void logout() {
        userController.logout();
        new ViewMyProfile().menuLogOut();
    }

    public void menuAdmin() {
        System.out.println("Hello ADMIN: " + currentUser.getName());
        System.out.println("1. Country Cigarette");
        System.out.println("2. Company Cigarette");
        System.out.println("3. Manager Cigarette");
        System.out.println("4. User Manage");
        System.out.println("5. Change Password");
        System.out.println("6. Log out");

        int choice = Config.getValidInteger();
        switch (choice) {
            case 1:
                new ViewCountry().menu();
                break;
            case 2:
                new ViewCompany().menu();
            case 3:
                new ViewCigarette().menu();
                break;
            case 4:
                formUserManage();
                break;
            case 5:
                formChangePassword();
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menuAdmin();
    }

    private void formChangePassword() {

        String oldPassword;
        while (true) {
            System.out.println("Enter your old password:");
            oldPassword = Config.scanner().nextLine();
            if (oldPassword.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid password, try again!");
            }
        }

        System.out.println("Enter your new password: ");
        String newPassword = Config.scanner().nextLine();

        System.out.println("Repeat the new password: ");
        String newPassword2 = Config.scanner().nextLine();

        if (!newPassword.equals(newPassword2)) {
            System.out.println("Repeat password incorrect");
        } else {
            ResponseMessenger messenger = userController.changePassword(oldPassword, newPassword);
            switch (messenger.getMessage()) {
                case "not_match":
                    System.out.println("Old password does not matches!");
                    break;
                case "success":
                    System.out.println("Change password successfully!");
                    logout();
            }
        }
    }


    private void formUserManage() {
        System.out.println("Menu");
        System.out.println("1. Show list user");
        System.out.println("2. Delete user");
        System.out.println("3. Change role");
        System.out.println("4. Block user");
        System.out.println("5. Back");

        int choice = Config.getValidInteger();
        if (choice == 0) return;
        switch (choice) {
            case 1:
                formShowListUser();
                break;
            case 2:
                formDeleteUser();
                break;
            case 3:
                formChangeRole();
                break;
            case 4:
                formBlockUser();
                break;
            case 5:
                return;
            default:
                System.out.print("Invalid choice : ");
        }

        formUserManage();
    }

    private void formBlockUser() {
        formShowListUser();
        System.out.println("Enter id user to block");
        int id = Config.getValidInteger();
        ResponseMessenger messenger = userController.blockUser(id);

        switch (messenger.getMessage()) {
            case "not_found":
                System.err.println("ID not found");
                break;
            case "blocked":
                System.out.println("You just blocked user id " + id);
                break;
            case "unblocked":
                System.out.println("You just unblocked user id " + id);
        }
    }

    private void formChangeRole() {
        formShowListUser();
        System.out.println("Enter id of user to change role");
        int id = Config.getValidInteger();
        System.out.println("Enter role to change (pm / user)");
        String roleName = Config.scanner().nextLine();

        ResponseMessenger messenger = userController.changeRole(id, roleName);

        switch (messenger.getMessage()) {
            case "success":
                System.out.println("Change role successfully!");
                break;
            case "invalid_role":
                System.err.println("Invalid role!");
                break;
            case "not_found":
                System.out.println("ID not found!");
        }

    }

    private void formDeleteUser() {
        formShowListUser();
        System.out.println("Enter id of user to delete");
        int id = Config.getValidInteger();
        ResponseMessenger messenger = userController.deleteUser(id);
        switch (messenger.getMessage()) {
            case "success":
                System.out.println("Delete user successfully");
                break;
            case "not_found":
                System.err.println("ID not found");
        }
    }

    private void formShowListUser() {
        List<User> users = userController.getUserList();
//        System.out.println(users);
        System.out.printf("%3s      %-12s %-7s %s\n", "ID", "USERNAME", "ROLE", "STATUS");
        for (User user : users) {
            System.out.printf("%3s      %-12s %-7s %s\n", user.getId(), user.getUsername(), user.getRoleName(), (user.isStatus() ? "BLOCKED" : "NOT BLOCKED"));
        }
    }
}
