package toby.spring.hellospring.payment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import toby.spring.hellospring.TestObjectFactory;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    ExRateProviderStub exRateProviderStub;

    @Test
//    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율 정보 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000L));

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(1_0000L));

        exRateProviderStub.setExRate(BigDecimal.valueOf(500));

        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율 정보 가져온다
        assertThat(payment2.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(500L));

        // 원화환산금액 계산
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(5000L));

        // 원화환산금액의 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));

    }

}