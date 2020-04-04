package lu.smarthome.externalsensors.provider.airquality.waqi;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {

    private Integer aqi;
    private Integer idx;
    private List<Attribution> attributions = null;
    private City city;
    private String dominentpol;
    private Iaqi iaqi;
    private Time time;
    private Debug debug;

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }
}
