package lu.smarthome.externalsensors.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.provider.air-quality.waqi")
public class WaqiAirQualityProperties {

    private String geoPrefix = "geo::";
    private String apiKey;
    private String url;
    private String city;
    private String lat;
    private String lng;

    public String getGeoPrefix() {
        return geoPrefix;
    }

    public void setGeoPrefix(String geoPrefix) {
        this.geoPrefix = geoPrefix;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getUrlWithLocation() {
        return getUrl() + "/" + getCity() + "/" ;
    }
}
