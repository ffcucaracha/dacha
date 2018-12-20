package hello.oracle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import hello.dao.UserRoleDAO;

public class UserRoleDAOImpl implements UserRoleDAO{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<String> getRoleUser() {
        Session session = this.sessionFactory.openSession();
        List<String> roles = session.createQuery("from UserRole").list();
        return roles;
    }
}
