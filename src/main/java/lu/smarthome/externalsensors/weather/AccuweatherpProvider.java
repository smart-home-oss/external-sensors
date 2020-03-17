package lu.smarthome.externalsensors.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AccuweatherpProvider implements WeatherProvider {

    @Qualifier("accuweather")
    private final RestTemplate restTemplate;

    @Override
    public void retrieve() {
    }

    @Override
    public boolean supports() {

        return true;
    }
}
