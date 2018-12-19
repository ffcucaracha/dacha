package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;


@Controller
public class GreetingController {

    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("message", this.message);
        return "index";
    }

    @RequestMapping("/bugaga")
    public String bugaga(@RequestParam(name="name", required=false, defaultValue="FPM-803") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting/bugaga";
    }


}
