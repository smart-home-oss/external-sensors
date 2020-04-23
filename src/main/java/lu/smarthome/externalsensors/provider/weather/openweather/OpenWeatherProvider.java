package lu.smarthome.externalsensors.provider.weather.openweather;

import lu.smarthome.externalsensors.config.OpenWeatherProperties;
import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.provider.FormatMode;
import lu.smarthome.externalsensors.provider.UnitsFormat;
import lu.smarthome.externalsensors.provider.weather.WeatherProvider;
import lu.smarthome.externalsensors.provider.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OpenWeatherProvider implements WeatherProvider {

    private final OpenWeatherProperties properties;

    private final RestTemplate restTemplate;

    public OpenWeatherProvider(OpenWeatherProperties properties, @Qualifier("generic") RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }


    @Override
    public String getName() {
        return "openweather";
    }

    @Override
    public WeatherResponse retrieve() {


        String url = UriComponentsBuilder
                .fromHttpUrl(properties.getUrl())
                .queryParam("q", properties.getCityStateAndCountry())
                .queryParam("units", UnitsFormat.METRIC)
                .queryParam("mode", FormatMode.JSON)
                .queryParam("lang", properties.getLang())
                .queryParam("appid", properties.getApiKey())
                .encode()
                .toUriString();

        ResponseEntity<OpenWeatherResponse> response = restTemplate
                .getForEntity(
                        url,
                        OpenWeatherResponse.class
                );
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
