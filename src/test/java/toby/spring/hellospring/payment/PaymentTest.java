package toby.spring.hellospring.payment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {

    @Test
    void createPrepared() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.createPrepared(1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1_000), LocalDateTime.now(clock));

        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(1_0000));
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.createPrepared(1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1_000), LocalDateTime.now(clock));

        assertThat(payment.isValid(clock)).isTrue();
        assertThat(payment.isValid(Clock.offset(clock, Duration.ofMinutes(30L)))).isFalse();
    }
}