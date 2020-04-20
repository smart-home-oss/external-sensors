package lu.smarthome.externalsensors.provider.airquality.waqi;

import lu.smarthome.externalsensors.config.properties.WaqiAirQualityProperties;
import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.provider.airquality.AirQualityProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component("waqi-airquality")
public class WaqiProvider implements AirQualityProvider {

    private final WaqiAirQualityProperties properties;

    private final RestTemplate restTemplate;

    public WaqiProvider(WaqiAirQualityProperties properties, @Qualifier("generic") RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "waqi";
    }

    @Override
    public WaqiResponse retrieve() {

        UriComponentsBuilder getParams = UriComponentsBuilder
                .fromHttpUrl(properties.getUrlWithLocation())
                .queryParam("token", properties.getApiKey());

        ResponseEntity<WaqiResponse> response;

        try {
            response = restTemplate
                    .getForEntity(
                            getParams.toUriString(),
                            WaqiResponse.class
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
    public boolean supports() {
        if (properties.getApiKey() == null) {
            return false;
        }

        return true;
    }
}
