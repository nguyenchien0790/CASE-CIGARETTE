package controller;

import dto.request.SignInDTO;
import dto.request.SignUpDTO;
import dto.response.ResponseMessenger;
import model.Role;
import model.RoleName;
import model.User;
import service.role.IRoleService;
import service.role.RoleServiceIMPL;
import service.user.IUserService;
import service.user.UserServiceIMPL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {

    IUserService userService = new UserServiceIMPL();
    User currentUser = userService.getCurrentUser();
    IRoleService roleService = new RoleServiceIMPL();

    public List<User> getUserList() {
        List<User> userList = new ArrayList<>(userService.findAll());
        userList.remove(0);
        return userList;
    }

    public ResponseMessenger register(SignUpDTO signUpDTO) {
        if (userService.existsByUsername(signUpDTO.getUsername())) {
            return new ResponseMessenger("user_existed");
        }
        if (userService.existsByEmail(signUpDTO.getEmail())) {
            return new ResponseMessenger("email_existed");
        }
        Set<String> strRole = signUpDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        for (String role : strRole) {
            switch (role) {
                case "admin":
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "user":
                    roles.add(roleService.findByRoleName(RoleName.USER));
                    break;
                default:
                    return new ResponseMessenger("invalid_role");
            }
        }

        User user = new User(
                signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getUsername(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword(),
                roles
        );

        userService.save(user);

        return new ResponseMessenger("success");
    }

    public ResponseMessenger login(SignInDTO signInDTO) {
        if (!userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())) {
            return new ResponseMessenger("login_failure");
        }
        if (userService.findByUsername(signInDTO.getUsername()).isStatus()) {
            return new ResponseMessenger("blocked");
        }

        User userLogin = userService.findByUsername(signInDTO.getUsername());

        userService.saveCurrentUser(userLogin);

        return new ResponseMessenger("login_success");
    }

    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    public void logout() {
        userService.saveCurrentUser(null);
    }

    public ResponseMessenger changePassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(currentUser.getPassword())) {
            return new ResponseMessenger("not_match");
        }
        currentUser.setPassword(newPassword);
        userService.updateData();
        return new ResponseMessenger("success");
    }

    public ResponseMessenger deleteUser(int id) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        userService.remove(id);
        return new ResponseMessenger("success");
    }

    public int getLastId() {
        return userService.getLastId();
    }

    public ResponseMessenger changeRole(int id, String roleName) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        if (!roleName.equals("USER") && !roleName.equals("PM")) {
            return new ResponseMessenger("invalid_role");
        }
        Role role = roleName.equals("USER") ? roleService.findByRoleName(RoleName.USER) : roleService.findByRoleName(RoleName.PM);
        userService.changeRole(id, role);
        return new ResponseMessenger("success");

    }

    public ResponseMessenger blockUser(int id) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        userService.changeStatus(id);
        boolean check = userService.findById(id).isStatus();
        if (check) {
            return new ResponseMessenger("blocked");
        } else {
            return new ResponseMessenger("unblocked");
        }
    }
}
