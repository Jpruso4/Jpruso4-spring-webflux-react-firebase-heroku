package co.com.sofka.questions.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AnswerDTO {

    @NotBlank(message = "Debe existir el userId para este objeto")
    private String userId;
    @NotBlank
    private String questionId;
    @NotBlank
    private String answer;

    private Integer position;

    private String id;


    public AnswerDTO(@NotBlank String questionId, @NotBlank String userId, @NotBlank String answer, String id) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.id = id;
    }

}
