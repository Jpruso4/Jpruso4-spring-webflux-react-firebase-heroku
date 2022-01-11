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

@SpringBootTest
public class UserGetUseCaseTest {

    @MockBean
    private UserRepository userRepository;
    @SpyBean
    private UserGetUseCase userGetUseCase;

    @Test
    void userGetUseCase(){

        var userDTO = new UserDTO(
                "xxxx",
                "Juan",
                "Gutierrez",
                "juanpablo01282002@gmail.com"
        );

        var user = new User();
        user.setId("xxxx");
        user.setName("Juan");
        user.setLastname("Gutierrez");
        user.setEmail("juanpablo01282002@gmail.com");

        Mockito.when(userRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(user));

        var respuesta = userGetUseCase.apply("xxxx");
        Assertions.assertEquals(respuesta.block().getId(), user.getId());
        Assertions.assertEquals(respuesta.block().getName(), user.getName());
        Assertions.assertEquals(respuesta.block().getLastname(), user.getLastname());
        Assertions.assertEquals(respuesta.block().getEmail(), user.getEmail());

    }
}
