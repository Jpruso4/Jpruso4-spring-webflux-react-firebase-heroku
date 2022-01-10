package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class AddAnswerUseCase implements SaveAnswer {

    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;
    private final GetUseCase getUseCase;
    private final EmailSenderUseCase emailSenderUseCase;

    public AddAnswerUseCase(AnswerRepository answerRepository, MapperUtils mapperUtils, GetUseCase getUseCase, EmailSenderUseCase emailSenderUseCase) {
        this.answerRepository = answerRepository;
        this.mapperUtils = mapperUtils;
        this.getUseCase = getUseCase;
        this.emailSenderUseCase = emailSenderUseCase;
    }

    public Mono<QuestionDTO> apply(AnswerDTO answerDTO) {
        Objects.requireNonNull(answerDTO.getQuestionId(), "Id of the question is required");
        return getUseCase.apply(answerDTO.getQuestionId()).flatMap(question ->
                answerRepository.save(mapperUtils.mapperToAnswer().apply(answerDTO))
                        .map(answer -> {
                            question.getAnswers().add(answerDTO);
                            emailSenderUseCase.sendEmail(question);
                            return question;
                        })
        );
    }
}
