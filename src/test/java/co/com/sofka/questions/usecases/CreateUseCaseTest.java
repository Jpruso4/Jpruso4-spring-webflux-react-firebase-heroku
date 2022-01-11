package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class CreateUseCaseTest {

    QuestionRepository questionRepository;
    CreateUseCase createUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        createUseCase = new CreateUseCase(mapperUtils, questionRepository);
    }

    @Test
    void create(){

        var questionDTO = new QuestionDTO(
                "1",
                "xxxx",
                "juanpablo01282002@gmail.com",
                "¿Que es un flux?",
                "OPEN (LONG OPEN BOX)",
                "SOFTWARE DEVELOPMENT"
        );

        var question = new Question(
                "1",
                "xxxx",
                "juanpablo01282002@gmail.com",
                "¿Que es un flux?",
                "OPEN (LONG OPEN BOX)",
                "SOFTWARE DEVELOPMENT"
        );

        when(questionRepository.save(Mockito.any())).thenReturn(Mono.just(question));

        StepVerifier.create(createUseCase.apply(questionDTO)).expectNextMatches(
                id ->{
                    assert id.equals(questionDTO.getId());
                    return true;
                }).verifyComplete();
        verify(questionRepository).save(Mockito.any(Question.class));
    }

}