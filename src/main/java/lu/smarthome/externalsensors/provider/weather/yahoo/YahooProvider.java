package lu.smarthome.externalsensors.provider.weather.yahoo;

import lu.smarthome.externalsensors.config.YahooProperties;
import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.oauth.OauthHelper;
import lu.smarthome.externalsensors.provider.weather.WeatherProvider;
import lu.smarthome.externalsensors.provider.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.springframework.http.HttpMethod.GET;

@Component
public class YahooProvider implements WeatherProvider {

    private final RestTemplate restTemplate;
    private final YahooProperties properties;
    private final OauthHelper oauthHelper;

    public YahooProvider(OauthHelper oauthHelper,
                         @Qualifier("yahoo") RestTemplate restTemplate,
                         YahooProperties properties) {
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
        String url = UriComponentsBuilder
                .fromHttpUrl(properties.getApiUrl())
                .queryParam("location", properties.getLocation())
                .toUriString();

        String oauthSignature = getSignature();

        HttpHeaders headers = getHttpHeaders(oauthSignature);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate
                .exchange(url, GET, request, String.class, Collections.emptyMap());

        if (response.getStatusCode().is2xxSuccessful()) {
            return response::getBody;
        }

        throw new ExternalSensorException(response.getStatusCode());
    }

    private String getSignature() {
        String hmac = oauthHelper.hmacSha1("data", properties.getClientSecret());
        return oauthHelper.toBase64(hmac);
    }

    private HttpHeaders getHttpHeaders(String oauthSignature) {
        HttpHeaders headers = new HttpHeaders();

        headers.add("X-Yahoo-App-Id", properties.getAppId());
        headers.add(
                HttpHeaders.AUTHORIZATION,
                "OAuth "
                        .concat("oauth_consumer_key=").concat(properties.getClientId())
                        .concat(",oauth_signature_method=").concat(properties.getOauthSignatureMethod())
                        .concat(",oauth_version=").concat(properties.getOauthVersion())
                        .concat(",oauth_nonce=").concat(oauthHelper.getNonce())
                        .concat(",oauth_timestamp=").concat(oauthHelper.getNowTimestamp())
                        .concat(",oauth_signature=").concat(oauthSignature)
        );
        return headers;
    }

    @Override
    public boolean supports() {
        return true;
    }
}


