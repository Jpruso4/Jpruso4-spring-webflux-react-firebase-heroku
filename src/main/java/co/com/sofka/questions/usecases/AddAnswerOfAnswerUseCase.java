package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class AddAnswerOfAnswerUseCase implements SaveAnswer {

    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;

    public AddAnswerOfAnswerUseCase(AnswerRepository answerRepository, MapperUtils mapperUtils) {
        this.answerRepository = answerRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<QuestionDTO> apply(AnswerDTO answerDTO) {
        Objects.requireNonNull(answerDTO.getQuestionId(),"Id of the answer is required" );

    }
}
