package lu.smarthome.externalsensors.weather;

import lombok.RequiredArgsConstructor;
import lu.smarthome.externalsensors.weather.accu.AccuweatherProvider;
import lu.smarthome.externalsensors.weather.yahoo.YahooProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherProviderSelector {

    public static final String ACCUWEATHER = "accuweather";
    public static final String YAHOO = "yahoo";
    private final AccuweatherProvider accuweatherpProvider;
    private final YahooProvider yahooProvider;

    @Value("${app.selected.provider}")
    //we will change this into a dynamic information that is retrieved from @Consul
    private String providerName;

    public WeatherProvider getProvider() {

        if (providerName.equals(ACCUWEATHER)) {

            return accuweatherpProvider;
        } else if (providerName.equals(YAHOO)) {
            return yahooProvider;
        }

        throw new RuntimeException("unknown provider : " + providerName);
    }
}
