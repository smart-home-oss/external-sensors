package lu.smarthome.externalsensors.provider.airquality.waqi;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class City {

    private List<Float> geo = null;
    private String name;
    private String url;

}
