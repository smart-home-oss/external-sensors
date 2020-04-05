package lu.smarthome.externalsensors.provider.airquality.airvisual;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pollution {

    private String ts; // "2017-02-01T01:15:00.000Z",
    private Integer aqius; // 18
    private String mainus; //main pollutant for US AQI
    private Integer aqicn; // 20
    private String maincn; //main pollutant for Chinese AQI
}