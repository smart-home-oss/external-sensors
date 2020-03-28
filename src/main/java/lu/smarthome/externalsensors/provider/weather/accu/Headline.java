package lu.smarthome.externalsensors.provider.weather.accu;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class Headline {

    private String effectiveDate;
    private Integer effectiveEpochDate;
    private Integer severity;
    private String text;
    private String category;
    private String endDate;
    private Integer endEpochDate;
    private String mobileLink;
    private String link;

}
