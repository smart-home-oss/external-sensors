package lu.smarthome.externalsensors.weather.accu;

import lu.smarthome.externalsensors.config.AccuweatherProperties;
import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.weather.WeatherProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component

public class AccuweatherProvider implements WeatherProvider {

    private final AccuweatherProperties accuweatherProperties;

    private final RestTemplate restTemplate;

    public AccuweatherProvider(AccuweatherProperties accuweatherProperties, @Qualifier("accuweather") RestTemplate restTemplate) {
        this.accuweatherProperties = accuweatherProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public AccuweatherResponse retrieve() {

        UriComponentsBuilder getParams = UriComponentsBuilder
                .fromHttpUrl(accuweatherProperties.getUrlWithLocation())
                .queryParam("apikey", accuweatherProperties.getApiKey())
                .queryParam("language", accuweatherProperties.getLanguage())
                .queryParam("details", accuweatherProperties.getDetails())
                .queryParam("metric", accuweatherProperties.getMetric());

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
        if(accuweatherProperties.getApiKey() == null){
            return false;
        }

        return true;
    }
}
