package lu.smarthome.externalsensors.service;

import org.springframework.stereotype.Service;

@Service
public class RomaniaWeatherService implements ExternalSensor {

    public void collect() {
        System.out.println("getting weather");
    //    make a http call
    //    parse the response
    //    push the response to a persistent storage
    }

}
