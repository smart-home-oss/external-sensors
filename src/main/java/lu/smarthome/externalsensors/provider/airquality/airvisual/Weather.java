package lu.smarthome.externalsensors.provider.airquality.airvisual;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {

    private String ts; //  "2017-02-01T01:00:00.000Z",
    private Integer tp; // 12
    private Integer pr; // 1020
    private Integer hu; // 62
    private Double ws; // 2
    private Integer wd; // 320
    private String ic; // "01n"

}