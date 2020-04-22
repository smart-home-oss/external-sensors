package lu.smarthome.externalsensors.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class WeatherProviderConfig {

    @Bean
    @Qualifier("generic")
    public RestTemplate restTemplateGeneric() {
        return new RestTemplate();
    }

    @Bean
    @Qualifier("accuweather")
    public RestTemplate restTemplateAccu() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;
    }

    @Bean
    @Qualifier("yahoo")
    public RestTemplate restTemplateYahoo() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;
    }

    @Bean
    @Qualifier("darksky")
    public RestTemplate restTemplateDarkSky() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;
    }

}
