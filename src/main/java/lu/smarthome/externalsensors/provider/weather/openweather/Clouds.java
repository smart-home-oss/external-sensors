package lu.smarthome.externalsensors.provider.weather.openweather;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Clouds {

    /**
     * Cloudiness, %
     * @see <a href="https://openweathermap.org/current">Official documentation</a>
     */
    private int all;

}
