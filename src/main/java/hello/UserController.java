package hello;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import models.User;
import dao.UserDAO;

@Controller
public class UserController {
    @GetMapping("/users/admin")
    public String  admin(Model model)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring2.xml");
        UserDAO userDAO = context.getBean(UserDAO.class);

        List<User> users = userDAO.getAllUsers();

        model.addAttribute("users", users);
        return "user/admin";
    }
}
