package lu.smarthome.externalsensors.weather;

public interface WeatherProvider {
    public void retrieve();
    public boolean supports();
}