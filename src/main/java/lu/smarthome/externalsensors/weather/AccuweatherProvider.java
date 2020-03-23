package lu.smarthome.externalsensors.weather;

import lombok.RequiredArgsConstructor;
import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.weather.accu.AccuweatherResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class AccuweatherProvider implements WeatherProvider {

    @Qualifier("accuweather")
    private final RestTemplate restTemplate;

    @Value("${app.selected.provider.accuweather.key}")
    private String apiKey;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public AccuweatherResponse retrieve() {
        String url = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/" + "130707_PC";
        String language = "en-US";
        Boolean details = Boolean.FALSE;
        Boolean metric = Boolean.TRUE;

        UriComponentsBuilder getParams = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apikey", apiKey)
                .queryParam("language", language)
                .queryParam("details", details)
                .queryParam("metric", metric);

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

        return true;
    }
}
