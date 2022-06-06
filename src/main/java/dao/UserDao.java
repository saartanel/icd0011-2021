package dao;

import config.ControllerConfig;
import mapper.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDao {

    @Transactional
    public User getUserByUserName(String userName) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
        EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);
        EntityManager em = factory.createEntityManager();

        return em.find(User.class, userName);
    }

    @Transactional
    public List<User> getALlUsers() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
        EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);
        EntityManager em = factory.createEntityManager();

        TypedQuery<User> query;
        query = em.createQuery("select u from User u", User.class);
        return query.getResultList();
    }
}
