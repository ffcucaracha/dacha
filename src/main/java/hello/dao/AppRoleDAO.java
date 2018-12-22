package hello.dao;

import hello.model.AppRole;

import java.util.List;

public interface AppRoleDAO {
    public List<String> getRoleNames(int userId);

    public List<AppRole> getAppRoles();

    public void saveAppRole(AppRole appRole);
}