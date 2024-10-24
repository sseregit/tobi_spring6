package toby.spring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import toby.spring.hellospring.data.JpaOrderRepository;
import toby.spring.hellospring.order.OrderRepository;
import toby.spring.hellospring.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    OrderService orderService(JpaTransactionManager transactionManager) {
        return new OrderService(orderRepository(), transactionManager);
    }

    @Bean
    OrderRepository orderRepository() {
        return new JpaOrderRepository();
    }
}
