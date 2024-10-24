package toby.spring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import toby.spring.hellospring.data.JdbcOrderRepository;
import toby.spring.hellospring.order.OrderRepository;
import toby.spring.hellospring.order.OrderService;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    OrderService orderService(
            OrderRepository orderRepository,
            PlatformTransactionManager transactionManager) {
        return new OrderService(orderRepository, transactionManager);
    }
}
