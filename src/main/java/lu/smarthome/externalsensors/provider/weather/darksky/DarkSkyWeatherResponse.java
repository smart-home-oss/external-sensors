package lu.smarthome.externalsensors.provider.weather.darksky;

import lombok.Getter;
import lombok.Setter;
import lu.smarthome.externalsensors.provider.weather.WeatherResponse;

@Getter
@Setter
public class DarkSkyWeatherResponse implements WeatherResponse {

    public Double latitude;
    public Double longitude;
    public String timezone;
    public Currently currently;
    public Hourly hourly;
    public Daily daily;
    public Flags flags;
    public Integer offset;

    @Override
    public String getTemp() {
        return String.valueOf(this.getCurrently().getTemperature());
    }
}
