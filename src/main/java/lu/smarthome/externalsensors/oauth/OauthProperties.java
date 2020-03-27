package lu.smarthome.externalsensors.oauth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Component
public class OauthProperties {
    String appId = "";
    String oauth_consumer_key = "";
    String oauth_nonce = "smart-home-oss" + String.valueOf(UUID.randomUUID());
    String oauth_signature_method = "HMAC-SHA1";

    public String getOauth_timestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    String oauth_timestamp = "";
    String oauth_version = "1.0";
}
