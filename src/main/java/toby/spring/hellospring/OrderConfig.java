package toby.spring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import toby.spring.hellospring.data.OrderRepository;
import toby.spring.hellospring.order.OrderService;

@Configuration
@Import(DataConfig.class)
class OrderConfig {

    @Bean
    OrderService orderService(JpaTransactionManager transactionManager) {
        return new OrderService(orderRepository(), transactionManager);
    }

    @Bean
    OrderRepository orderRepository() {
        return new OrderRepository();
    }
}
