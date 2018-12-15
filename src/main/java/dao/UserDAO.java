package dao;

import java.util.List;
import models.User;

public interface UserDAO {
    public void saveUser(User user);
    public User getUser(int id);
    public void updateUser(User user);
    // public void deletePaper(int id);
    public List<User> getAllUsers();
}
