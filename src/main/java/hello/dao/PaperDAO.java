package hello.dao;

import java.util.List;
import hello.model.Paper;

public interface PaperDAO {
    public void savePaper(Paper paper);
   // public Paper getPaperById(int id);
    //public void updatePaper(Paper paper);
   // public void deletePaper(int id);
    public List<Paper> getAllPapers();
}
