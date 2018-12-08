package hello;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import paper.*;

@Controller
public class PaperController {
    @GetMapping("/papers/all")
    public String viewPapers(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        PaperDAO paperDAO = context.getBean(PaperDAO.class);

        List<Paper> list = paperDAO.getAllPapers();

       String text = new String();
        for(Paper p : list) {
            text += "<tr>" + p + "</tr>";
        }
        model.addAttribute("text", text);
        return "papers";
    }

   /* @GetMapping("/papers/init")
    public String init() {
        Paper paper = new Paper();
        paper.setName("Food");
        paper.setAuthor("Anna");
        paper.setText("llalalalalalal");

        PaperDAO.save(paper);

    }*/

}