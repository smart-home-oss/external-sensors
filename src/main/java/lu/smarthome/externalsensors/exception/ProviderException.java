package lu.smarthome.externalsensors.exception;

import lu.smarthome.externalsensors.provider.ProviderType;

public class ProviderException extends RuntimeException {

    private final ProviderType providerType;
    private final String providerName;

    public ProviderException(ProviderType providerType, String providerName) {
        this.providerType = providerType;
        this.providerName = providerName;
    }

    @Override
    public String getMessage() {
        return "Unknown provider, type: " + providerType + ", name: " + providerName;
    }
}
