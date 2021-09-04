package crudApp.service;

import crudApp.models.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void add(User user);
    void delete(int id);
    void update(User user);
    User getById(int id);
}
