package toby.spring.hellospring.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import toby.spring.hellospring.OrderConfig;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSource;

    @Test
    void createOrder() {
        var order = orderService.createOrder("0100", BigDecimal.ONE);

        assertThat(order.getId()).isGreaterThan(0);
        assertThat(order.getNo()).isEqualTo("0100");
        assertThat(order.getTotal()).isEqualByComparingTo(BigDecimal.ONE);
    }

    @Test
    void createOrders() {
        var orderReqs = List.of(new OrderReq("0200", BigDecimal.ONE), new OrderReq("0300", BigDecimal.TWO));

        var orders = orderService.createOrders(orderReqs);

        assertThat(orders).hasSize(2);
        orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0));

    }

    @Test
    void createDuplicatedOrders() {
        var orderReqs = List.of(new OrderReq("0300", BigDecimal.ONE), new OrderReq("0300", BigDecimal.TWO));

        assertThatThrownBy(() -> orderService.createOrders(orderReqs))
                .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient jdbcClient = JdbcClient.create(dataSource);

        Long no = jdbcClient.sql("""
                            select count(*) from orders where no = :no
                        """)
                .param("no", "0300")
                .query(Long.class).single();


        assertThat(no).isEqualTo(0);
    }
}