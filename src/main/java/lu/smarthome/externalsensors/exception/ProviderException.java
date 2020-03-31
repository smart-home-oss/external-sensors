package lu.smarthome.externalsensors.exception;

import lu.smarthome.externalsensors.provider.ProviderType;

import java.util.Arrays;
import java.util.Set;

public class ProviderException extends RuntimeException {

    private ProviderType providerType;
    private String providerName;
    private Set<String> knownProviders;
    private String message;

    public ProviderException(ProviderType providerType, String providerName, Set<String> knownProviders) {
        this.providerType = providerType;
        this.providerName = providerName;
        this.knownProviders = knownProviders;
    }

    public ProviderException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        if(message != null) {
            return message;
        }

        return "Unknown provider, type: " + providerType
                + ", name: " + providerName
                + ", known providers: " + Arrays.toString(knownProviders.toArray());
    }
}
