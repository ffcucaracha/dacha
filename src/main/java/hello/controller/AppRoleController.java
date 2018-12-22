package hello.controller;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import hello.model.AppRole;
import hello.dao.AppRoleDAO;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppRoleController {
    @GetMapping("/role/admin")
    public String admin(Model model)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppRoleDAO appRoleDAO = context.getBean(AppRoleDAO.class);

        List<AppRole> roles = appRoleDAO.getAppRoles();

        model.addAttribute("roles", roles);
        return "role/admin";
    }

    @RequestMapping("/role/save")
    public String save()
    {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            AppRoleDAO appRoleDAO = context.getBean(AppRoleDAO.class);

            AppRole appRole = new AppRole();
            appRole.setRole_name("Cucumber");

            appRoleDAO.saveAppRole(appRole);

        return "redirect:admin";
    }
}
