package lu.smarthome.externalsensors.provider.weather.darksky;

import lu.smarthome.externalsensors.config.properties.DarkSkyWeatherProperties;
import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.provider.weather.WeatherProvider;
import lu.smarthome.externalsensors.provider.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component

public class DarkSkyProvider implements WeatherProvider {

    private final DarkSkyWeatherProperties properties;

    private final RestTemplate restTemplate;

    public DarkSkyProvider(DarkSkyWeatherProperties properties, @Qualifier("darksky") RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "darksky";
    }

    @Override
    public WeatherResponse retrieve() {

        UriComponentsBuilder getParams = UriComponentsBuilder
                .fromHttpUrl(properties.getUrlWithLocation())
                .queryParam("units", properties.getUnits());

        ResponseEntity<DarkSkyWeatherResponse> response = restTemplate
                .getForEntity(
                        getParams.toUriString(),
                        DarkSkyWeatherResponse.class
                );
        if (response.getStatusCode().is2xxSuccessful()) {

            return response.getBody();
        }

        throw new ExternalSensorException(response.getStatusCode());
    }

    @Override
    public boolean isHealthy() {
        if (properties.getAppId() == null) {
            return false;
        }

        return true;
    }
}
