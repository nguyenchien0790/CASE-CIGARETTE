package service.user;

import model.Role;
import model.User;
import service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean checkLogin(String username, String password);

    User getCurrentUser();

    void saveCurrentUser(User user);

    User findByUsername(String username);

    void changeRole(int id, Role role);

    void changeStatus(int id);
}
