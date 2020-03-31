package lu.smarthome.externalsensors.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.provider.weather.weatherbit")
public class WeatherbitWeatherProperties {

    private String apiKey;
    private String url;
    private String city;
    private String county;
    private String lang;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
