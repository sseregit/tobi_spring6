package toby.spring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import toby.spring.hellospring.order.Order;

public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }
}
