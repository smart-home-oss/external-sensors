package lu.smarthome.externalsensors.sensor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lu.smarthome.externalsensors.provider.ProviderType;
import lu.smarthome.externalsensors.provider.airquality.AirQualityProvider;
import lu.smarthome.externalsensors.provider.airquality.AirQualityProviderSelector;
import lu.smarthome.externalsensors.provider.airquality.AirQualityResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirQualitySensor implements ExternalSensor {

    private final AirQualityProviderSelector providerSelector;

    @Scheduled(fixedRateString = "${app.sensor.scheduled-rate.air-quality:600000}")
    public void collect() {

        AirQualityProvider provider = providerSelector.getProvider();

        if (provider.supports()) {
            AirQualityResponse response = provider.retrieve();
            log.info(ProviderType.AIR_QUALITY + ": " + response.getIndex());
        } else {
            log.warn("The requested provider is not supported, provider name: " + provider.getName());
        }
    }
}
