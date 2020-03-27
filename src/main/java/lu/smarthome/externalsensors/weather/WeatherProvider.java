package lu.smarthome.externalsensors.weather;

import lu.smarthome.externalsensors.weather.accu.AccuweatherResponse;

public interface WeatherProvider {
    public String getName();
    public WeatherResponse retrieve();
    public boolean supports();
}