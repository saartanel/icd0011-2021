package dao;

import config.ControllerConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import mapper.Order;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderDao {

    @Transactional
    public void saveOrder(Order order) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
        EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
    }

    @Transactional
    public Order getOrderById(Long id) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
        EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);
        EntityManager em = factory.createEntityManager();

        return em.find(Order.class, id);
    }

    @Transactional
    public List<Order> getAllOrders() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
        EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);
        EntityManager em = factory.createEntityManager();

        TypedQuery<Order> query;
        query = em.createQuery("select o from Order o", Order.class);
        return query.getResultList();
    }
}
