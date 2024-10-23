package toby.spring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import toby.spring.hellospring.exrate.ExRateData;

import java.math.BigDecimal;

public interface ExRateExtractor {
    BigDecimal extract(String response) throws JsonProcessingException;
}
