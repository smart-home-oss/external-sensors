package lu.smarthome.externalsensors.provider.weather;

import lombok.extern.slf4j.Slf4j;
import lu.smarthome.externalsensors.config.properties.AppProperties;
import lu.smarthome.externalsensors.exception.ProviderException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lu.smarthome.externalsensors.provider.ProviderType.WEATHER;

@Component
@Slf4j
public class WeatherProviderSelector {

    private final AppProperties appProperties;
    private Map<String, WeatherProvider> providers;

    public WeatherProviderSelector(AppProperties appProperties,
                                   List<WeatherProvider> providers) {
        this.appProperties = appProperties;
        this.providers = providers
                .stream()
                .collect(
                        Collectors.toMap(WeatherProvider::getName, p -> p));
    }

    @PostConstruct
    public void postConstruct() {
        if(appProperties.getWeatherProvider() == null) {
            log.error(WEATHER + "provider was not set, the collection will fail.");
        }
    }

    public WeatherProvider getProvider() {

        if (providers.containsKey(appProperties.getWeatherProvider())) {
            return providers.get(appProperties.getWeatherProvider());
        }

        throw new ProviderException(WEATHER, appProperties.getWeatherProvider(), providers.keySet());
    }
}
