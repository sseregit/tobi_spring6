package toby.spring.hellospring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toby.spring.hellospring.order.Order;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        // em
        EntityManager em = emf.createEntityManager();
        // transaction
        em.getTransaction().begin();

        // em.persist
        Order order = new Order("100", BigDecimal.TEN);
        em.persist(order);
        System.out.println("order = " + order);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
