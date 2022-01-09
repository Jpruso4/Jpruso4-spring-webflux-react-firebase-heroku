package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.UserDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface UpdateUser {

    Mono<UserDTO> apply(@Valid UserDTO userDTO);
}
