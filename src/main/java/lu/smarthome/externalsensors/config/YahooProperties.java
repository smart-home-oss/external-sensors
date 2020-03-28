package lu.smarthome.externalsensors.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("app.provider.yahoo")
@Component
public class YahooProperties {

    private String apiUrl = "https://weather-ydn-yql.media.yahoo.com/forecastrss";
    private String appId;

    /**
     * Client ID, known as Consumer Key
     */
    private String clientId;

    /**
     * Client Secret, known as Consumer Secret
     */
    private String clientSecret;

    private String locality = "trier";
    private String countryCode = "de";

    public String getLocation() {
        return locality.concat(",").concat(countryCode);
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getConsumerKey() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getOauthSignatureMethod() {
        return "HMAC-SHA1";
    }

    public String getOauthVersion() {
        return "1.0";
    }
}
