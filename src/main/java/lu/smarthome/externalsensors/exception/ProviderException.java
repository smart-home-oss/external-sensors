package lu.smarthome.externalsensors.exception;

import lu.smarthome.externalsensors.provider.ProviderType;

import java.util.Arrays;
import java.util.Set;

public class ProviderException extends RuntimeException {

    private final ProviderType providerType;
    private final String providerName;
    private final Set<String> knownProviders;

    public ProviderException(ProviderType providerType, String providerName, Set<String> knownProviders) {
        this.providerType = providerType;
        this.providerName = providerName;
        this.knownProviders = knownProviders;
    }

    @Override
    public String getMessage() {
        return "Unknown provider, type: " + providerType
                + ", name: " + providerName
                + ", known providers: " + Arrays.toString(knownProviders.toArray());
    }
}
