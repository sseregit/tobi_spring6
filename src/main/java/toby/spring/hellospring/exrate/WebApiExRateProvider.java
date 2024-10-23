package toby.spring.hellospring.exrate;

import org.springframework.stereotype.Component;
import toby.spring.hellospring.api.ApiTemplate;
import toby.spring.hellospring.api.ErApiExRateExtractor;
import toby.spring.hellospring.api.HttpClientApiExecutor;
import toby.spring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;

@Component
public class WebApiExRateProvider implements ExRateProvider {

    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());

    }


}
