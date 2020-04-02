package lu.smarthome.externalsensors.provider.airquality;

public interface AirQualityProvider {

    String getName();

    AirQualityResponse retrieve();

    boolean supports();
}