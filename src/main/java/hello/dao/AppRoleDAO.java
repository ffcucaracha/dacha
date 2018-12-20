package hello.dao;

import hello.entity.UserRole;

import java.util.List;

public interface AppRoleDAO {
    public List<String> getRoleNames(int userId);
}