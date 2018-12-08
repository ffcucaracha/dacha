package paper;

import java.util.List;

public interface PaperDAO {
    public void savePaper(Paper paper);
   // public Paper getPaperById(int id);
    //public void updatePaper(Paper paper);
   // public void deletePaper(int id);
    public List<Paper> getAllPapers();
}
