package lu.smarthome.externalsensors.provider.weather.yahoo;

import lu.smarthome.externalsensors.config.properties.YahooWeatherProperties;
import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.oauth.OauthHelper;
import lu.smarthome.externalsensors.provider.weather.WeatherProvider;
import lu.smarthome.externalsensors.provider.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Component
public class YahooProvider implements WeatherProvider {

    private final RestTemplate restTemplate;
    private final YahooWeatherProperties properties;
    private final OauthHelper oauthHelper;

    public YahooProvider(OauthHelper oauthHelper,
                         @Qualifier("yahoo") RestTemplate restTemplate,
                         YahooWeatherProperties properties) {
        this.restTemplate = restTemplate;
        this.oauthHelper = oauthHelper;
        this.properties = properties;
    }

    @Override
    public String getName() {
        return "yahoo";
    }

    @Override
    public WeatherResponse retrieve() {

        // https://developer.yahoo.com/weather/documentation.html#code-example

        String url = getUrl();
        String nonce = oauthHelper.getNonce();
        String timestamp = oauthHelper.getNowTimestamp();
        String signatureString = getSignatureString(nonce, timestamp);
        String oauthSignature = getSignature(signatureString);

        HttpHeaders headers = getHttpHeaders(oauthSignature);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate
                .exchange(url, GET, request, String.class, Collections.emptyMap());

        if (response.getStatusCode().is2xxSuccessful()) {
            return response::getBody;
        }

        throw new ExternalSensorException(response.getStatusCode());
    }

    @Override
    public boolean isHealthy() {
        return true;
    }

    String getUrl() {
        return UriComponentsBuilder
                .fromHttpUrl(properties.getApiUrl())
                .queryParam("location", properties.getLocation())
                .queryParam("format", "json")
                .toUriString();
    }

    String getSignature(String signatureString) {
        byte[] hmac = oauthHelper.hmacSha1(signatureString, properties.getClientSecret() + "&");
        return oauthHelper.toBase64(hmac);
    }

    String getSignatureString(String nonce, String timestamp) {
        List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key=" + properties.getConsumerKey());
        parameters.add("oauth_nonce=" + nonce);
        parameters.add("oauth_signature_method=" + properties.getOauthSignatureMethod());
        parameters.add("oauth_timestamp=" + timestamp);
        parameters.add("oauth_version=" + properties.getOauthVersion());
        try {
            parameters.add("location=" + URLEncoder.encode(properties.getLocation(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        parameters.add("format=json");
        Collections.sort(parameters);

        StringBuilder parametersList = new StringBuilder();
        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append((i > 0) ? "&" : "").append(parameters.get(i));
        }

        try {
            return GET.name() + "&" +
                    URLEncoder.encode(properties.getApiUrl(), "UTF-8") + "&" +
                    URLEncoder.encode(parametersList.toString(), "UTF-8");
        } catch (Exception e) {

        }

        throw new RuntimeException();
    }

    HttpHeaders getHttpHeaders(String oauthSignature) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Yahoo-App-Id", properties.getAppId());
        headers.add(
                HttpHeaders.AUTHORIZATION,
                "OAuth "
                        .concat("oauth_consumer_key=\"").concat(properties.getClientId())
                        .concat("\", oauth_nonce=\"").concat(oauthHelper.getNonce())
                        .concat("\", oauth_timestamp=\"").concat(oauthHelper.getNowTimestamp())
                        .concat("\", oauth_signature_method=\"").concat(properties.getOauthSignatureMethod())
                        .concat("\", oauth_signature=\"").concat(oauthSignature)
                        .concat("\", oauth_version=\"").concat(properties.getOauthVersion())
                        .concat("\"")
        );

        return headers;
    }

}


