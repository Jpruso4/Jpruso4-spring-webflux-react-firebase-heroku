package co.com.sofka.questions.collections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Question {
    @Id
    private String id;
    private String userId;
    private String userEmail;
    private String question;
    private String type;
    private String category;
}
