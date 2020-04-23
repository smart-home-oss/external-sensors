package lu.smarthome.externalsensors.provider.weather;

public interface WeatherProvider {

    String getName();

    WeatherResponse retrieve();

    boolean isHealthy();
}