package hello.oracle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.List;

import hello.model.Paper;
import hello.dao.PaperDAO;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.query.Query;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

public class PaperDAOImpl implements PaperDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void savePaper(Paper paper) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        /*session.doWork(conn->{
            paper.setContent_file(BlobProxy.generateProxy(file));
        });*/
        session.persist(paper);
        tx.commit();
        session.close();
        /*Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {

            //session.persist(p);
            //transaction.begin();

            //paper.setName("Try blob");
            session.doWork(conn->{
                paper.setContent_file(BlobProxy.generateProxy(getImage()));
            });
            session.save(paper);
            transaction.commit();

            System.out.println("Product is saved successfully.");

        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Transaction is being rolled back.");
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }

        }*/

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
        //Paper paper = (Paper) session.load(Paper.class, id);

        Query query = session.createQuery("from Paper where id = :id");
        query.setParameter("id",id);
        List<Paper> papers = query.list();
        session.close();
        Paper paper = papers.get(0);
        return paper;
    }

    public static byte[] getImage(){
        File file =new File("Java.png");
        if(file.exists()){
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", byteOutStream);
                return byteOutStream.toByteArray();
            } catch (IOException ei) {
                ei.printStackTrace();
                //throw ei;
            }
        }
        return null;
    }
   }
