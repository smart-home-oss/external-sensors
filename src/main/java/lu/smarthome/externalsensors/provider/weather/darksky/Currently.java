package lu.smarthome.externalsensors.provider.weather.darksky;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Currently {

    public Integer time;
    public String summary;
    public String icon;
    public Integer precipIntensity;
    public Integer precipProbability;
    public Double temperature;
    public Double apparentTemperature;
    public Double dewPoint;
    public Double humidity;
    public Integer pressure;
    public Double windSpeed;
    public Double windGust;
    public Integer windBearing;
    public Double cloudCover;
    public Integer uvIndex;
    public Integer visibility;
    public Double ozone;

}
