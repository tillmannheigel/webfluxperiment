package de.tillmannheigel.xperimentz.reactiveProducingApplication.services;

import de.tillmannheigel.xperimentz.model.Temperature;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * Created by ou on 05.06.17.
 */
@Service
public class WeatherService {

    public Temperature getTemperatureForZipcodeDE(double zipcode){
        return new Temperature(25.5);
    }

}
