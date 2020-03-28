package lu.smarthome.externalsensors.provider.weather.accu;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lu.smarthome.externalsensors.provider.weather.WeatherResponse;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class AccuWeatherResponse implements WeatherResponse {

    private Headline headline;
    private List<DailyForecast> dailyForecasts = null;

    @Override
    public String getTemp() {
        return String.valueOf(this.getDailyForecasts().get(0).getTemperature().getMaximum().getValue());
    }
}
