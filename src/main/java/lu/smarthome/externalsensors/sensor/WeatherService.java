package lu.smarthome.externalsensors.sensor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lu.smarthome.externalsensors.weather.WeatherProvider;
import lu.smarthome.externalsensors.weather.WeatherProviderSelector;
import lu.smarthome.externalsensors.weather.accu.AccuweatherResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService implements ExternalSensor {

    private final WeatherProviderSelector weatherProviderSelector;

    @Scheduled(fixedRateString = "${app.sensor.weather.collect:600000}")
    public void collect() {
        WeatherProvider weatherProvider = weatherProviderSelector.getProvider();
        if (weatherProvider.supports()) {
            AccuweatherResponse response = weatherProvider.retrieve();
            log.info(String.valueOf(response.getDailyForecasts().get(0).getTemperature().getMaximum().getValue()));
        } else {
            log.warn("The requested provider is not supported, provider name: " + weatherProvider.getName());
        }
    }
}
