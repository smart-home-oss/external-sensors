package lu.smarthome.externalsensors.provider.airquality.airvisual;

import lu.smarthome.externalsensors.config.properties.AirVisualAirQualityProperties;
import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.provider.airquality.AirQualityProvider;
import lu.smarthome.externalsensors.provider.airquality.AirQualityResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Qualifier("air-quality")
@Component("airvisual-airquality")
public class AirVisualProvider implements AirQualityProvider {

    private final AirVisualAirQualityProperties properties;

    private final RestTemplate restTemplate;

    public AirVisualProvider(AirVisualAirQualityProperties properties, @Qualifier("generic") RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "airvisual";
    }

    @Override
    public AirQualityResponse retrieve() {

        UriComponentsBuilder getParams = UriComponentsBuilder
                .fromHttpUrl(properties.getUrl())
                .queryParam("key", properties.getApiKey())
                .queryParam("city", properties.getCity())
                .queryParam("state", properties.getState())
                .queryParam("country", properties.getCountry());

        ResponseEntity<AirVisualResponse> response;

        try {
            response = restTemplate
                    .getForEntity(
                            getParams.encode().build().toUri(),
                            AirVisualResponse.class
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
