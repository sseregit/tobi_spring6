package toby.spring.hellospring.order;

import java.math.BigDecimal;

record OrderReq(
        String no,
        BigDecimal total
) {
}
