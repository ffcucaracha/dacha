package hello.controller;

import java.util.List;

import hello.dao.UserDAO;
import hello.entity.AppUser;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import hello.entity.AppRole;
import hello.dao.AppRoleDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController {
    @GetMapping("/role/admin")
    public String admin(Model model)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppRoleDAO appRoleDAO = context.getBean(AppRoleDAO.class);

        List<String> roles = appRoleDAO.getRoleNames(2);

        model.addAttribute("roles", roles);
        return "role/admin";
    }
}
