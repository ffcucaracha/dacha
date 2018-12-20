package hello.controller;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import hello.model.User;
import hello.entity.AppUser;
import hello.dao.UserDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @GetMapping("/users/admin")
    public String  admin(Model model)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring2.xml");
        UserDAO userDAO = context.getBean(UserDAO.class);

        List<AppUser> users = userDAO.getAllUsers();

        model.addAttribute("users", users);
        return "user/admin";
    }

    @RequestMapping("/users/view")
    public String view(@RequestParam(name="name", required=false, defaultValue="dbuser1") String name, Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserDAO userDAO = context.getBean(UserDAO.class);
        AppUser appUser = userDAO.getUserByUsername(name);
        model.addAttribute("user", appUser);
        return "user/view";
    }
}
