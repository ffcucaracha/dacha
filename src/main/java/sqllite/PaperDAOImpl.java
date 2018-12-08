package sqllite;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import paper.*;

public class PaperDAOImpl implements PaperDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void savePaper(Paper p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
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
   }
