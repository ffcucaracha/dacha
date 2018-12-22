package hello.oracle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import hello.model.AppRole;
import hello.dao.AppRoleDAO;

public class AppRoleDAOImpl implements AppRoleDAO{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<String> getRoleNames(int userId) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("select ur.appRole.role_name from UserRole ur where ur.appUser.user_id = :userid");
        query.setParameter("userid",userId);
        //List<AppUser> user_l = session.createQuery("from AppUser").list();
        List<String> roles = query.list();
        session.close();
        //AppUser appUser = user_l.get(0);
        return roles;
    }

    @Override
    public List<AppRole> getAppRoles()
    {
        Session session = this.sessionFactory.openSession();
        List<AppRole> roles = session.createQuery("from AppRole").list();
        session.close();
        return roles;
    }

    @Override
    public void saveAppRole(AppRole appRole)
    {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(appRole);

        transaction.commit();
        session.close();
    }

}