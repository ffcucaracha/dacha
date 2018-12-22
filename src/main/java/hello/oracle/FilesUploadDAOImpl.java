package hello.oracle;

import hello.model.FilesUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hello.dao.FilesUploadDAO;

import java.util.List;

@Repository
public class FilesUploadDAOImpl implements FilesUploadDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	@Transactional
	public void save(FilesUpload filesUpload) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(filesUpload);
		transaction.commit();
		session.close();
		//sessionFactory.getCurrentSession().save(filesUpload);

	}

	@Override
	public FilesUpload getFileById(int id)
	{
		Session session = this.sessionFactory.openSession();
		FilesUpload file = session.load(FilesUpload.class,id);
		return file;
	}

	@Override
	public List<FilesUpload> getAllFilesFromPaper(int paper_id)
	{
		Session session = this.sessionFactory.openSession();
		List<FilesUpload> files = session.createQuery("from FilesUpload where paper_id = :paper_id")
				.setParameter("paper_id",paper_id)
				.list();

		return files;
	}

}
