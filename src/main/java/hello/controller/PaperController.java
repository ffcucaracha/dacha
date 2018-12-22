package hello.controller;

import java.io.IOException;
import java.util.List;

import hello.dao.FilesUploadDAO;
import hello.model.FilesUpload;
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
    @GetMapping("/paper/admin")
    public String admin(Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		PaperDAO paperDAO = context.getBean(PaperDAO.class);

        List<Paper> papers = paperDAO.getAllPapers();

        model.addAttribute("papers", papers);
        model.addAttribute("new_paper",new Paper());
        return "paper/admin";
    }

    @RequestMapping("/paper/save")
    public String save(@RequestParam(name="name") String name,@RequestParam(name="author") String author,@RequestParam(name="text") String text)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        PaperDAO paperDAO = context.getBean(PaperDAO.class);

        Paper paper = new Paper();
        paper.setName(name);
        paper.setAuthor(author);
        paper.setText(text);

        paperDAO.savePaper(paper);
        return "redirect:admin";
    }


    /*
    @RequestMapping(value = "paper/doUpload")
    public String handleFileUpload(HttpServletRequest request, @RequestParam MultipartFile fileUpload) throws Exception {
        if (fileUpload != null) {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring2.xml");
            PaperDAO paperDAO = context.getBean(PaperDAO.class);

            System.out.println("Saving file: " + fileUpload.getOriginalFilename());

                Paper paper = new Paper();
                paper.setName("c картинкой");
                paper.setAuthor("zzz");
                paper.setText("yyyyyyy");
                //paper.setContent_file(fileUpload.getBytes());
                paperDAO.savePaper(paper);
        }
        return "paper/papers";
    }*/

    @RequestMapping("paper/view")
    public String view(@RequestParam(name="id", required=false, defaultValue="75") int id, Model model)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        PaperDAO paperDAO = context.getBean(PaperDAO.class);
        FilesUploadDAO filesUploadDAO = context.getBean(FilesUploadDAO.class);

        Paper paper = paperDAO.getPaperById(id);
        model.addAttribute("paper", paper);

        List<FilesUpload> files = filesUploadDAO.getAllFilesFromPaper(id);
        model.addAttribute("files",files);

         return "paper/view";
    }

    @RequestMapping("paper/upload")
    public String upload(@RequestParam(name="paper_id") int paper_id,
                         @RequestParam(name="filesUpload") MultipartFile fileUpload) throws Exception
    {
        if (fileUpload != null){// && fileUpload.length > 0) {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            FilesUploadDAO fileUploadDAO = context.getBean(FilesUploadDAO.class);
            //for (CommonsMultipartFile aFile : fileUpload){

                FilesUpload uploadFile = new FilesUpload();
                uploadFile.setPaper_id(paper_id);
                uploadFile.setFileName(fileUpload.getOriginalFilename());
                uploadFile.setData(fileUpload.getBytes());
                fileUploadDAO.save(uploadFile);
           // }
        }
        return "redirect:admin";
    }


    @RequestMapping("paper/download-document")
    public String downloadDocument(@RequestParam(name="id") int id, HttpServletResponse response) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        FilesUploadDAO fileUploadDAO = context.getBean(FilesUploadDAO.class);

        FilesUpload file = fileUploadDAO.getFileById(id);
        response.setContentType(file.getDataType());
        response.setContentLength(file.getData().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + file.getFileName() +"\"");

        FileCopyUtils.copy(file.getData(), response.getOutputStream());

        return "redirect:admin";
    }

    @RequestMapping("paper/image")
    public void showImage(@RequestParam("id") int id, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        FilesUploadDAO fileUploadDAO = context.getBean(FilesUploadDAO.class);

        FilesUpload file = fileUploadDAO.getFileById(id);

        response.setContentType(file.getDataType());
        response.getOutputStream().write(file.getData());
        //Content-Type: text/html; charset=utf-8

        response.getOutputStream().close();
    }

}
