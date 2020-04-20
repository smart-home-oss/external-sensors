package lu.smarthome.externalsensors.provider.weather.openweather;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lu.smarthome.externalsensors.provider.weather.WeatherResponse;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OpenWeatherResponse implements WeatherResponse {

    private Coord coord;
    private List<Weather> weather = null;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;
    private Snow snow;
    private int dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    @Override
    public String getTemp() {
        return String.valueOf(this.getMain().getTemp());
    }
}
