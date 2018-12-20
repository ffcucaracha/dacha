package hello.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import hello.model.Paper;
import hello.dao.PaperDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        return "paper/papers";
    }

    @RequestMapping("/paper/save")
    public String savePaper(@RequestParam(name="file", required=false, defaultValue="75") MultipartFile file, Model model)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        PaperDAO paperDAO = context.getBean(PaperDAO.class);

        Paper paper = new Paper();
        paper.setName("try");
        paper.setAuthor("anna");
        paper.setText("lalala");
        paper.setContent_file(file);


        String message = "nothing";
        try{
            paperDAO.savePaper(paper);
            message = "сохранение прошло успешно";
        } catch (Exception e) {
            message = "сохранение не удалось";
        } finally {
            model.addAttribute("message", message);
            return "/index";
        }


    }

    @RequestMapping("paper/view")
    public String view(@RequestParam(name="id", required=false, defaultValue="75") int id, Model model)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        PaperDAO paperDAO = context.getBean(PaperDAO.class);

        Paper paper = paperDAO.getPaperById(id);
        model.addAttribute("paper", paper);

        return "paper/view";
    }

   /* @GetMapping("/papers/init")
    public String init() {
        Paper paper = new Paper();
        paper.setName("Food");
        paper.setAuthor("Anna");
        paper.setText("llalalalalalal");

        PaperDAO.save(paper);

    }*/

    @RequestMapping("paper/download-document")
    public String downloadDocument(@RequestParam(name="name", required=false, defaultValue="FPM-803") String name, Model model, HttpServletResponse response) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        PaperDAO paperDAO = context.getBean(PaperDAO.class);

        Paper paper = paperDAO.getPaperById(75);
        response.setContentType("image/png");
        response.setContentLength(paper.getContent_file().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + paper.getName() +"\"");

        FileCopyUtils.copy(paper.getContent_file(), response.getOutputStream());

        return "redirect:/papers";
    }

    @RequestMapping("paper/image")
    public void showImage(@RequestParam("id") Integer itemId, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        PaperDAO paperDAO = context.getBean(PaperDAO.class);

        Paper paper = paperDAO.getPaperById(75);

        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.setContentLength(paper.getContent_file().length);
        response.getOutputStream().write(paper.getContent_file());
        //Content-Type: text/html; charset=utf-8


        response.getOutputStream().close();
    }

}
