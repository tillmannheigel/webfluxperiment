package de.tillmannheigel.xperimentz.reactiveProducingApplication.controllers;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.tillmannheigel.xperimentz.model.Event;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class BasicController {

    @GetMapping("/event/{id}")
    Mono<Event> eventById(@PathVariable long id){
        return Mono.just(new Event(id, new Date()));
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/events")
    Flux<Event> events(){
        Flux<Event> eventFlux = Flux.fromStream(
                Stream.generate(() -> new Event(System.currentTimeMillis(), new Date()))
        );
        Flux<Long> heartbeatFlux = Flux.interval(Duration.ofMillis(234));
        return Flux.zip(eventFlux, heartbeatFlux).map(Tuple2::getT1);
    }

}
