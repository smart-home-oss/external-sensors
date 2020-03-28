package lu.smarthome.externalsensors.oauth;

import lombok.extern.slf4j.Slf4j;
import lu.smarthome.externalsensors.exception.OauthHelperException;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.UUID;

@Component
@Slf4j
public class OauthHelper {

    private HmacSha1Signature hmacSha1Signature = new HmacSha1Signature();

    public String getNonce() {
        return "smart-home-oss" + UUID.randomUUID();
    }

    public String getNowTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public String hmacSha1(String data, String key) {
        try {
            return hmacSha1Signature.calculateRFC2104HMAC(data, key);
        } catch (Exception e) {
            log.error("Cannot build hmac sha1 signature, e: {}", e.getMessage());
        }

        throw new OauthHelperException();
    }

    public String toBase64(String hmac) {
        return Base64.getEncoder().encodeToString(hmac.getBytes());
    }
}
