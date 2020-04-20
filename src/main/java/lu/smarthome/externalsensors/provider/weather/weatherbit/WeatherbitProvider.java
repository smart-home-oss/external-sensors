package lu.smarthome.externalsensors.provider.weather.weatherbit;

import lu.smarthome.externalsensors.config.properties.WeatherbitWeatherProperties;
import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.provider.weather.WeatherProvider;
import lu.smarthome.externalsensors.provider.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Qualifier("weather")
@Component("weatherbit-weather")
public class WeatherbitProvider implements WeatherProvider {

    private final WeatherbitWeatherProperties properties;

    private final RestTemplate restTemplate;

    public WeatherbitProvider(WeatherbitWeatherProperties properties, @Qualifier("generic") RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "weatherbit";
    }

    @Override
    public WeatherResponse retrieve() {

        UriComponentsBuilder getParams = UriComponentsBuilder
                .fromHttpUrl(properties.getUrl())
                .queryParam("key", properties.getApiKey())
                .queryParam("lang", properties.getLang())
                .queryParam("city", properties.getCity())
                .queryParam("country", properties.getCountry());

        ResponseEntity<WeaterbitResponse> response;

        try {
            response = restTemplate
                    .getForEntity(
                            getParams.toUriString(),
                            WeaterbitResponse.class
                    );
        } catch (HttpServerErrorException e) {
            throw new ExternalSensorException(e.getStatusCode());
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new ExternalSensorException(response.getStatusCode());
    }

    @Override
    public boolean isHealthy() {
        if (properties.getApiKey() == null) {
            return false;
        }

        return true;
    }
}
