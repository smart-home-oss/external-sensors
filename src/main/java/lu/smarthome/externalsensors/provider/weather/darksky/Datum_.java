package lu.smarthome.externalsensors.provider.weather.darksky;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Datum_ {

    public Integer time;
    public String summary;
    public String icon;
    public Integer sunriseTime;
    public Integer sunsetTime;
    public Double moonPhase;
    public Double precipIntensity;
    public Double precipIntensityMax;
    public Integer precipIntensityMaxTime;
    public Double precipProbability;
    public String precipType;
    public Double temperatureHigh;
    public Integer temperatureHighTime;
    public Double temperatureLow;
    public Integer temperatureLowTime;
    public Double apparentTemperatureHigh;
    public Integer apparentTemperatureHighTime;
    public Double apparentTemperatureLow;
    public Integer apparentTemperatureLowTime;
    public Double dewPoint;
    public Double humidity;
    public Double pressure;
    public Double windSpeed;
    public Double windGust;
    public Integer windGustTime;
    public Integer windBearing;
    public Double cloudCover;
    public Integer uvIndex;
    public Integer uvIndexTime;
    public Integer visibility;
    public Double ozone;
    public Double temperatureMin;
    public Integer temperatureMinTime;
    public Double temperatureMax;
    public Integer temperatureMaxTime;
    public Double apparentTemperatureMin;
    public Integer apparentTemperatureMinTime;
    public Double apparentTemperatureMax;
    public Integer apparentTemperatureMaxTime;

}
