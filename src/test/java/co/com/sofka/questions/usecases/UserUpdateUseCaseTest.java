package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserUpdateUseCaseTest {

    @SpyBean
    private UserUpdateUseCase userUpdateUseCase;

    @MockBean
    private UserRepository userRepository;

    @Test
    void userUpdateUseCaseTest(){

        var userDTO = new UserDTO(
                "xxxx",
                "Juan",
                "Diaz",
                "juan@gmail.com"
        );

        var user = new User(
                "xxxx",
                "Juan",
                "Gutierrez",
                "juanpablo01282002@gmail.com"
        );

        when(userRepository.save(Mockito.any())).thenReturn(Mono.just(user));

        var result = userUpdateUseCase.apply(userDTO);

        Assertions.assertEquals(Objects.requireNonNull(result.block()).getId(), "xxxx");

        Mockito.verify(userRepository, Mockito.times(1)).save(any());
    }
}
