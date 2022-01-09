package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.usecases.UserCreateUseCase;
import co.com.sofka.questions.usecases.UserGetUseCase;
import co.com.sofka.questions.usecases.UserUpdateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> createUser(UserCreateUseCase userCreateUseCase){
        Function<UserDTO, Mono<ServerResponse>> executor = userDTO -> userCreateUseCase.apply(userDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/createUser").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDTO.class).flatMap(executor));
    }

    @Bean
    public RouterFunction<ServerResponse> getUser(UserGetUseCase userGetUseCase){
        return route(
                GET("/getUser/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(userGetUseCase.apply(
                            request.pathVariable("id")),
                            UserDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateUser(UserUpdateUseCase userUpdateUseCase){
        return route(PUT("/updateUser").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDTO.class)
                        .flatMap(userDTO -> userUpdateUseCase.apply(userDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
}
