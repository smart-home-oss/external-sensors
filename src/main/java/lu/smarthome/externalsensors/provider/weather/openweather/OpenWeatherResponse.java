package lu.smarthome.externalsensors.provider.weather.openweather;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lu.smarthome.externalsensors.provider.weather.WeatherResponse;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class OpenWeatherResponse implements WeatherResponse {

    private Coordinates coord;
    private List<Weather> weather;
    private String base;
    private Temperature main;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private GeneralData sys;
    private long timezone;
    private long id;
    private String name;
    private Integer cod;

    @Override
    public String getTemp() {
        return String.valueOf(this.getMain().getTemp_max());
    }
}
