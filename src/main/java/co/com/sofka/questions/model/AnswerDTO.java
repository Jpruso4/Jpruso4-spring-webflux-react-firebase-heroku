package co.com.sofka.questions.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Data
public class AnswerDTO {


    @NotBlank(message = "Debe existir el userId para este objeto")
    private String userId;
    @NotBlank
    private String questionId;
    @NotBlank
    private String answer;

    private Integer position;

    private List<AnswerDTO> answersOfAnswers;

    public AnswerDTO(@NotBlank String questionId, @NotBlank String userId, @NotBlank String answer) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public List<AnswerDTO> getAnswers() {
        this.answersOfAnswers = Optional.ofNullable(answersOfAnswers).orElse(new ArrayList<>());
        return answersOfAnswers;
    }

}
