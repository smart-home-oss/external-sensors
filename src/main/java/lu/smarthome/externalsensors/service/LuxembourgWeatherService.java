package lu.smarthome.externalsensors.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LuxembourgWeatherService implements ExternalSensor {

    @Scheduled(fixedRateString = "${app.sensor.weather.luxembourg:600000}")
    public void collect() {
        System.out.println("getting weather");
    //    make a http call
    //    parse the response
    //    push the response to a persistent storage
    }

}
