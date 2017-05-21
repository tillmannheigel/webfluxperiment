package de.tillmannheigel.xperimentz.reactiveclient.controller;

import java.nio.file.Files;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.tillmannheigel.xperimentz.reactiveclient.model.Event;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by tillmannheigel on 21.05.17.
 */
@RestController
public class BasicController {

    @GetMapping("/event/{id}")
    Mono<Event> eventById(@PathVariable long id){
        return Mono.just(new Event(id, new Date()));
    }

    @GetMapping("/events")
    Flux<Event> events(){
        Flux<Event> eventFlux = Flux.fromStream(
                Stream.generate(() -> new Event(System.currentTimeMillis(), new Date()))
        );
        Flux<Long> heartbeatFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(eventFlux, heartbeatFlux).map(objects -> objects.getT1());
    }

}
