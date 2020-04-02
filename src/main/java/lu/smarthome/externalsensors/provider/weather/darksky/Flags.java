package lu.smarthome.externalsensors.provider.weather.darksky;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Flags {

    public List<String> sources = null;
    public String meteoalarmLicense;
    public Double nearestStation;
    public String units;

}
