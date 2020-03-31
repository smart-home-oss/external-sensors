package lu.smarthome.externalsensors.provider.weather.darksky;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Hourly {

    public String summary;
    public String icon;
    public List<Datum> data = null;

}
