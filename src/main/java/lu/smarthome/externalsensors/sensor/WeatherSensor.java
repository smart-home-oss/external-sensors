package lu.smarthome.externalsensors.sensor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lu.smarthome.externalsensors.provider.ProviderType;
import lu.smarthome.externalsensors.provider.weather.WeatherProvider;
import lu.smarthome.externalsensors.provider.weather.WeatherProviderSelector;
import lu.smarthome.externalsensors.provider.weather.WeatherResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherSensor implements ExternalSensor {

    private final WeatherProviderSelector weatherProviderSelector;

    @Scheduled(fixedRateString = "${app.sensor.scheduled-rate.weather:600000}")
    public void collect() {

        WeatherProvider weatherProvider = weatherProviderSelector.getProvider();

        if (weatherProvider.isHealthy()) {
            WeatherResponse response = weatherProvider.retrieve();
            log.info(ProviderType.WEATHER + ": " + response.getTemp());
        } else {
            log.warn("The requested provider is not healthy, provider name: " + weatherProvider.getName());
        }
    }
}
