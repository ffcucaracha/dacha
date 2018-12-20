package hello.service;

import java.util.ArrayList;
import java.util.List;

import hello.dao.AppRoleDAO;
import hello.dao.UserDAO;
import hello.entity.AppUser;
import hello.oracle.AppRoleDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    //@Autowired
    //private UserDAO appUserDAO;

    //@Autowired
    //private AppRoleDAOImpl appRoleDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //AppUser appUser = this.appUserDAO.findUserAccount(userName);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring2.xml");
        UserDAO userDAO = context.getBean(UserDAO.class);

        AppRoleDAO appRoleDAO = context.getBean(AppRoleDAO.class);


        AppUser appUser = userDAO.getUserByUsername(userName);


        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = appRoleDAO.getRoleNames(appUser.getUser_id());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUser_name(), //
                appUser.getEncrypted_password(), grantList);

        return userDetails;
    }

}