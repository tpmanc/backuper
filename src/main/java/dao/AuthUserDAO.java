package dao;

import authentication.CustomUser;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AuthUserDAO  {
    @Autowired
    private SessionFactory sessionFactory;

    public AuthUserDAO() {
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public CustomUser getByEmail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();
        CustomUser authUser = new CustomUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getEmail());
        authUser.setPassword(user.getPasswordHash());
        return authUser;
    }
}
