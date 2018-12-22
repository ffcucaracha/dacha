package hello.controller;

import java.util.List;

import hello.dao.AppUserDAO;
import hello.utils.EncryptedPasswordUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import hello.model.AppUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppUserController {
    @GetMapping("/user/admin")
    public String  admin(Model model)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppUserDAO appUserDAO = context.getBean(AppUserDAO.class);

        List<AppUser> users = appUserDAO.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("new_user", new AppUser());
        return "user/admin";
    }

    @RequestMapping("/user/view")
    public String view(@RequestParam(name="name", required=false, defaultValue="dbuser1") String name, Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppUserDAO appUserDAO = context.getBean(AppUserDAO.class);
        AppUser appUser = appUserDAO.getUserByUsername(name);
        model.addAttribute("user", appUser);
        return "user/view";
    }

    @RequestMapping("/user/save")
    public String save(@RequestParam(name="user_name") String name,@RequestParam(name="encrypted_password") String password)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppUserDAO appUserDAO = context.getBean(AppUserDAO.class);
        EncryptedPasswordUtils encryptedPasswordUtils = new EncryptedPasswordUtils();

        AppUser appUser = new AppUser();
        appUser.setUser_name(name);
        appUser.setEncrypted_password(encryptedPasswordUtils.encryptePassword(password));
        appUser.setEnabled(true);

        appUserDAO.saveUser(appUser);
        return "redirect:admin";
    }
}
