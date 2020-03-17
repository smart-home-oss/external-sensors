package lu.smarthome.externalsensors.service;

import lombok.RequiredArgsConstructor;
import lu.smarthome.externalsensors.weather.WeatherProviderSelector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GermanyWeatherService implements ExternalSensor {

    private final WeatherProviderSelector weatherProviderSelector;

    @Scheduled(fixedRateString = "${app.sensor.weather.collect:600000}")
    public void collect() {

        weatherProviderSelector.getProvider().retrieve();
        //    make a http call
        //    parse the response
        //    push the response to a persistent storage
    }

}
