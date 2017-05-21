package de.tillmannheigel.xperimentz.reactiveConsumptionApplication;

import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import de.tillmannheigel.xperimentz.model.Event;

@SpringBootApplication
public class ReactiveConsumptionApplication {

    @Bean
    WebClient webClient(){
        return WebClient.create("http://localhost:8080");
    }

    @Bean
    CommandLineRunner cmd (WebClient client){
        return args -> {
            client.get().uri("/events")
                    .accept(MediaType.TEXT_EVENT_STREAM)
                    .exchange()
                    .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Event.class))
                    .subscribe(System.out::println);
        };
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ReactiveConsumptionApplication.class)
                .properties(Collections.singletonMap("server.port", "8081"))
                .run(args);
    }
}
