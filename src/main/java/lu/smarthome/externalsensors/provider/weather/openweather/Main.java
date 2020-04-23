package lu.smarthome.externalsensors.provider.weather.openweather;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Main {

    private float temp;
    private float feelsLike;
    private float tempMin;
    private float tempMax;
    private int pressure;
    private int humidity;
    private int seaLevevl;
    private int grndLevel;

}
