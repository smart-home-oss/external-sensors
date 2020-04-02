package lu.smarthome.externalsensors.provider.airquality.weatherbit;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lu.smarthome.externalsensors.exception.ProviderException;
import lu.smarthome.externalsensors.provider.airquality.AirQualityResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WeaterbitResponse implements AirQualityResponse {

    public Integer lat;
    public Integer lon;
    public String timezone;
    public String cityName;
    public String countryCode;
    public String stateCode;
    public List<Datum> data = new ArrayList<>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String getIndex() {
        if(data.size() < 1) {
            throw new ProviderException("Could not get enough data");
        }

        return data.get(0).co.toString();
    }
}