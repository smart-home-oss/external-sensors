package lu.smarthome.externalsensors.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherProviderSelector {

    public static final String ACCUWEATHER = "accuweather";
    private final AccuweatherProvider accuweatherpProvider;

    @Value("${app.selected.provider}")
    //we will change this into a dynamic information that is retrieved from @Consul
    private String providerName;

    public WeatherProvider getProvider() {

        if (providerName.equals(ACCUWEATHER)) {

            return accuweatherpProvider;
        }
        throw new RuntimeException("unknown provider : " + providerName);
    }
}
