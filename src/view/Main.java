package view;

import controller.UserController;
import model.User;

public class Main {

    UserController userController = new UserController();

    public Main() {
        User currentUser = userController.getCurrentUser();
        if (currentUser == null) {
            new ViewMyProfile().menuLogOut();
        } else {
            new ViewMyProfile().menuLogin();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
