package controller;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import model.Paper;
import dao.PaperDAO;

@Controller
public class PaperController {
    @GetMapping("/papers/all")
    public String viewPapers(Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring2.xml");
		PaperDAO paperDAO = context.getBean(PaperDAO.class);

        List<Paper> papers = paperDAO.getAllPapers();

       /*String text = new String();
        for(Paper p : papers) {
            text += "<tr>" + p + "</tr>";
        }*/
        //model.addAttribute("text", text);

        model.addAttribute("papers", papers);
        return "paper/papers.jsp";
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
