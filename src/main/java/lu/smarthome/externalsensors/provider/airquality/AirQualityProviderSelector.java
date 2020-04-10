package lu.smarthome.externalsensors.provider.airquality;

import lombok.extern.slf4j.Slf4j;
import lu.smarthome.externalsensors.config.properties.AppProperties;
import lu.smarthome.externalsensors.exception.ProviderException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lu.smarthome.externalsensors.provider.ProviderType.AIR_QUALITY;

@Component
@Slf4j
public class AirQualityProviderSelector {

    private final AppProperties appProperties;
    private Map<String, AirQualityProvider> providers;

    public AirQualityProviderSelector(AppProperties appProperties,
                                      List<AirQualityProvider> providers) {
        this.appProperties = appProperties;
        this.providers = providers
                .stream()
                .collect(
                        Collectors.toMap(AirQualityProvider::getName, p -> p));
    }

    @PostConstruct
    public void postConstruct() {
        if(appProperties.getAirQualityProvider() == null) {
            log.error(AIR_QUALITY + "provider was not set, the collection will fail.");
        }
    }

    public AirQualityProvider getProvider() {

        if (providers.containsKey(appProperties.getAirQualityProvider())) {
            return providers.get(appProperties.getAirQualityProvider());
        }

        throw new ProviderException(AIR_QUALITY, appProperties.getAirQualityProvider(), providers.keySet());
    }
}
