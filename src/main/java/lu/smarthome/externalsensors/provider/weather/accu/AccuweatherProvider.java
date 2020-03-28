package lu.smarthome.externalsensors.provider.weather.accu;

import lu.smarthome.externalsensors.config.AccuweatherProperties;
import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.provider.weather.WeatherProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component

public class AccuweatherProvider implements WeatherProvider {

    private final AccuweatherProperties properties;

    private final RestTemplate restTemplate;

    public AccuweatherProvider(AccuweatherProperties properties, @Qualifier("accuweather") RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "accuweather";
    }

    @Override
    public AccuweatherResponse retrieve() {

        UriComponentsBuilder getParams = UriComponentsBuilder
                .fromHttpUrl(properties.getUrlWithLocation())
                .queryParam("apikey", properties.getApiKey())
                .queryParam("language", properties.getLanguage())
                .queryParam("details", properties.getDetails())
                .queryParam("metric", properties.getMetric());

        ResponseEntity<AccuweatherResponse> response = restTemplate
                .getForEntity(
                        getParams.toUriString(),
                        AccuweatherResponse.class
                );
        if (response.getStatusCode().is2xxSuccessful()) {

            return response.getBody();
        }

        throw new ExternalSensorException(response.getStatusCode());
    }

    @Override
    public boolean supports() {
        if(properties.getApiKey() == null){
            return false;
        }

        return true;
    }
}
