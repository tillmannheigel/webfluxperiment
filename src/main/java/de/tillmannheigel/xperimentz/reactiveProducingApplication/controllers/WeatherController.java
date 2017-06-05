package de.tillmannheigel.xperimentz.reactiveProducingApplication.controllers;

import de.tillmannheigel.xperimentz.model.Temperature;
import de.tillmannheigel.xperimentz.reactiveProducingApplication.services.WeatherService;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * Created by ou on 05.06.17.
 */
@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/temperature/{zipcode}")
    Mono<Temperature> temperatureForZipcode(@PathVariable long zipcode){
        return Mono.just(weatherService.getTemperatureForZipcodeDE(zipcode));
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/temperatureStream/{zipcode}")
    Flux<Temperature> temperatureStreamForZipcode(@PathVariable long zipcode){
        Flux<Temperature> templFlux = Flux.fromStream(
                Stream.generate(() -> weatherService.getTemperatureForZipcodeDE(zipcode))
        );
        //slow stream a bit down
        Flux<Long> heartbeatFlux = Flux.interval(Duration.ofMillis(1000));
        return Flux.zip(templFlux, heartbeatFlux).map(Tuple2::getT1);
    }
}
