package lu.smarthome.externalsensors.oauth;

import lombok.extern.slf4j.Slf4j;
import lu.smarthome.externalsensors.exception.OauthHelperException;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

@Component
@Slf4j
public class OauthHelper {
    private static final String HMAC_SHA1 = "HmacSHA1";

    private Random rand = new Random();

    public String getNonce() {
        byte[] nonce = new byte[32];
        rand.nextBytes(nonce);
        return new String(nonce).replaceAll("\\W", "");
    }

    public String getNowTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public byte[] hmacSha1(String data, String key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1);
            Mac mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);

            return mac.doFinal(data.getBytes());
        } catch (Exception e) {
            log.error("Cannot build hmac sha1 signature, e: {}", e.getMessage());
        }

        throw new OauthHelperException();
    }

    public String toBase64(byte[] hmac) {
        return Base64.getEncoder().encodeToString(hmac);
    }
}
