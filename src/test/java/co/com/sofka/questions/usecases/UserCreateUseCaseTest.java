package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.reposioties.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class UserCreateUseCaseTest {

    UserRepository userRepository;
    UserCreateUseCase userCreateUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        userRepository = mock(UserRepository.class);
        userCreateUseCase = new UserCreateUseCase(userRepository, mapperUtils);
    }

    @Test
    void userCreate(){

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

        when(userRepository.save(Mockito.any())).thenReturn(Mono.just(user));

        StepVerifier.create(userCreateUseCase.apply(userDTO)).expectNextMatches(
                id ->{
                    assert id.equals(userDTO.getId());
                    return true;
                }).verifyComplete();
        verify(userRepository).save(Mockito.any(User.class));
    }
}
