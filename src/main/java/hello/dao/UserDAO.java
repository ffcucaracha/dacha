package hello.dao;

import java.util.List;

import hello.entity.AppUser;

public interface UserDAO {
    public void saveUser(AppUser appUser);
    public AppUser getUserById(int id);
    public AppUser getUserByUsername(String username);
    //public void updateUser(User user);
    // public void deletePaper(int id);
    public List<AppUser> getAllUsers();
}
