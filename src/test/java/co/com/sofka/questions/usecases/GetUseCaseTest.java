package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class GetUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;
    @SpyBean
    private GetUseCase getUseCase;

    @Test
    void getUseCase(){

        var questionDto = new QuestionDTO("1",
                "1",
                "juanpablo01282002@gmail.com",
                "¿Que es un mono?",
                "OPEN (LONG OPEN BOX)",
                "SOFTWARE DEVELOPMENT");

        var question = new Question();
        question.setId("1");
        question.setUserId("1");
        question.setUserEmail("juanpablo01282002@gmail.com");
        question.setQuestion("¿Que es un mono?");
        question.setType("OPEN (LONG OPEN BOX)");
        question.setCategory("SOFTWARE DEVELOPMENT");

        Mockito.when(questionRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(question));

        var respuesta = getUseCase.apply("1");
        Assertions.assertEquals(respuesta.block().getId(), question.getId());
        Assertions.assertEquals(respuesta.block().getUserId(), question.getUserId());
        Assertions.assertEquals(respuesta.block().getUserEmail(), question.getUserEmail());
        Assertions.assertEquals(respuesta.block().getQuestion(), question.getQuestion());
        Assertions.assertEquals(respuesta.block().getType(), question.getType());
        Assertions.assertEquals(respuesta.block().getCategory(), question.getCategory());

        Mockito.verify(questionRepository, Mockito.times(1)).findById("1");

    }
}