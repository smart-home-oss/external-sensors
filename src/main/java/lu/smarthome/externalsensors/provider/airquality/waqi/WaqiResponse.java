package lu.smarthome.externalsensors.provider.airquality.waqi;

import lombok.Getter;
import lombok.Setter;
import lu.smarthome.externalsensors.provider.airquality.AirQualityResponse;

@Getter
@Setter
public class WaqiResponse implements AirQualityResponse {

    private String status;
    private Data data;

    @Override
    public String getIndex() {
        return String.valueOf(data.getAqi());
    }
}
