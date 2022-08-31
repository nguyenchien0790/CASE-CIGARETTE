package service.user;

import config.Config;
import model.Role;
import model.RoleName;
import model.User;
import service.role.RoleServiceIMPL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceIMPL implements IUserService {

    static String PATH_USER = "src/database/user.txt";
    static String PATH_USER_LOGIN = "src/database/user_login.txt";
    static Config<List<User>> config = new Config<>();

    static List<User> userList = config.read(PATH_USER);

    static {
        if (userList == null || userList.size() == 0) {
            userList = new ArrayList<>();
            Set<Role> roles = new HashSet<>();
            roles.add(new RoleServiceIMPL().findByRoleName(RoleName.ADMIN));
            userList.add(
                    new User(
                            0,
                            "CHIEN",
                            "admin",
                            "admin@admin.admin",
                            "admin",
                            roles
                    )
            );
        }
    }


    @Override
    public List<User> findAll() {
        config.write(PATH_USER, userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        updateData();
    }

    @Override
    public void remove(int id) {
        userList.remove(findById(id));
        updateData();
    }

    @Override
    public User findById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updateData() {
        config.write(PATH_USER, userList);
    }

    @Override
    public int getLastId() {
        return userList.get(userList.size() - 1).getId() + 1;
    }

    @Override
    public void edit(User user) {

    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        User user = new Config<User>().read(PATH_USER_LOGIN);
        if (user == null) return null;
        return findByUsername(user.getUsername());
    }

    @Override
    public void saveCurrentUser(User user) {
        new Config<User>().write(PATH_USER_LOGIN, user);
    }

    @Override
    public User findByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void changeRole(int id, Role role) {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        findById(id).setRoles(roles);
        updateData();
    }

    @Override
    public void changeStatus(int id) {
        User user = findById(id);
        user.setStatus(!user.isStatus());
        updateData();
    }
}
