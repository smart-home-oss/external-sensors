package lu.smarthome.externalsensors.provider.weather.weatherbit;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Datum {

    private Integer rh;
    private String pod;
    private Double lon;
    private Double pres;
    private String timezone;
    private String obTime;
    private String countryCode;
    private Integer clouds;
    private Integer ts;
    private Integer solarRad;
    private String stateCode;
    private String cityName;
    private Double windSpd;
    private String lastObTime;
    private String windCdirFull;
    private String windCdir;
    private Double slp;
    private Double vis;
    private Double hAngle;
    private String sunset;
    private Integer dni;
    private Double dewpt;
    private Integer snow;
    private Integer uv;
    private Integer precip;
    private Integer windDir;
    private String sunrise;
    private Integer ghi;
    private Integer dhi;
    private Integer aqi;
    private Double lat;
    private Weather weather;
    private String datetime;
    private Double temp;
    private String station;
    private Double elevAngle;
    private Double appTemp;
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}