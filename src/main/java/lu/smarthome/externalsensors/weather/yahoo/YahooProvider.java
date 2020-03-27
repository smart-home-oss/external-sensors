package lu.smarthome.externalsensors.weather.yahoo;

import lu.smarthome.externalsensors.exception.ExternalSensorException;
import lu.smarthome.externalsensors.oauth.OauthProperties;
import lu.smarthome.externalsensors.weather.WeatherProvider;
import lu.smarthome.externalsensors.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Collections;

import static lu.smarthome.externalsensors.oauth.HmacSha1Signature.calculateRFC2104HMAC;

@Component
public class YahooProvider implements WeatherProvider {


    private final RestTemplate restTemplate;

    private final OauthProperties oauthProperties;

    public YahooProvider(OauthProperties oauthProperties, @Qualifier("yahoo") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.oauthProperties = oauthProperties;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public WeatherResponse retrieve() {

        String location = "trier";
        String locationCountry = "de";

        String hmac = null;
        try {
            hmac = calculateRFC2104HMAC("data", oauthProperties.getOauth_consumer_key());
        } catch (SignatureException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        String oauth_signature = Base64.getEncoder().encodeToString(hmac.getBytes());


        UriComponentsBuilder getParams = UriComponentsBuilder
                .fromHttpUrl("https://weather-ydn-yql.media.yahoo.com/forecastrss")
                .queryParam("location", location.concat(",").concat(locationCountry));


        HttpHeaders headers = new HttpHeaders();

        headers.add("X-Yahoo-App-Id", oauthProperties.getAppId());
        headers.add(
                "Authorization",
                "OAuth "
                        .concat("oauth_consumer_key=")
                        .concat(oauthProperties.getOauth_consumer_key())
                        .concat(",oauth_signature_method=")
                        .concat(oauthProperties.getOauth_signature_method())
                        .concat(",oauth_timestamp=")
                        .concat(oauthProperties.getOauth_timestamp())
                        .concat(",oauth_nonce=")
                        .concat(oauthProperties.getOauth_nonce())
                        .concat(",oauth_version=")
                        .concat(oauthProperties.getOauth_version())
                        .concat(",oauth_signature=")
                        .concat(oauth_signature)
        );

        HttpEntity<String> request = new HttpEntity(null, headers);


        ResponseEntity<String> response = restTemplate
                .exchange(
                        getParams.toUriString(),
                        HttpMethod.GET,
                        request,
                        String.class,
                        Collections.emptyMap()
                );
        if (response.getStatusCode().is2xxSuccessful()) {

            return response::getBody;
        }

        throw new ExternalSensorException(response.getStatusCode());
    }

    @Override
    public boolean supports() {
        return true;
    }
}


