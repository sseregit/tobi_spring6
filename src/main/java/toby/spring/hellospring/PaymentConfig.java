package toby.spring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import toby.spring.hellospring.exrate.CachedExRateProvider;
import toby.spring.hellospring.exrate.RestTemplateExRateProvider;
import toby.spring.hellospring.payment.ExRateProvider;
import toby.spring.hellospring.payment.PaymentService;

import java.time.Clock;

@Configuration
class PaymentConfig {
    @Bean
    PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
