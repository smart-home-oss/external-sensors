package lu.smarthome.externalsensors.provider.weather.accu;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class DailyForecast {

    private String date;
    private Integer epochDate;
    private Temperature temperature;
    private Day day;
    private Night night;
    private List<String> sources = null;
    private String mobileLink;
    private String link;

}
