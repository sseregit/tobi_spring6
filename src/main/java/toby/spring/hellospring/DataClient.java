package toby.spring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toby.spring.hellospring.data.OrderRepository;
import toby.spring.hellospring.order.Order;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);

        orderRepository.save(order);

        Order order2 = new Order("100", BigDecimal.ONE);

        orderRepository.save(order2);
    }
}
