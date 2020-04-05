package lu.smarthome.externalsensors.provider.airquality.airvisual;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    private String city; // "Berlin"
    private String state; // "Berlin"
    private String country; // "Germany"
    private Location location;
    private Current current;

}