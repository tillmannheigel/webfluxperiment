package de.tillmannheigel.xperimentz.reactiveProducingApplication.controllers;

import de.tillmannheigel.xperimentz.model.Temperature;
import de.tillmannheigel.xperimentz.reactiveProducingApplication.services.WeatherService;
import org.reactivestreams.Subscriber;
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
    @GetMapping("/temperature/{zipcode}")
    Mono<Temperature> temperatureForZipcode(@PathVariable long zipcode){
        return Mono.just(new Temperature(25.5));
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/temperatureStream/{zipcode}")
    Flux<Temperature> temperatureStreamForZipcode(@PathVariable long zipcode){
        return Flux.fromStream(
                Stream.generate(() -> new Temperature(25.5))
        );
    }
}
