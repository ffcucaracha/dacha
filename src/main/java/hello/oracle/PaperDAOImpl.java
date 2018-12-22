package hello.oracle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import hello.model.Paper;
import hello.dao.PaperDAO;

public class PaperDAOImpl implements PaperDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

   @Override
   public void savePaper(Paper paper) {
       Session session = this.sessionFactory.openSession();
       Transaction transaction = session.beginTransaction();
       session.save(paper);
       transaction.commit();
       session.close();
   }

    @SuppressWarnings("unchecked")
    @Override
    public List<Paper> getAllPapers() {
        Session session = this.sessionFactory.openSession();
        List<Paper> paperList = session.createQuery("from Paper").list();
        session.close();
        return paperList;
    }

    @Override
    public Paper getPaperById(int id)
    {
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("from Paper where id = :id");
        query.setParameter("id",id);
        List<Paper> papers = query.list();
        session.close();
        Paper paper = papers.get(0);
        return paper;
    }

}
