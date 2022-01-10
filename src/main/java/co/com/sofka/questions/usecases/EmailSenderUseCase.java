package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.QuestionDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderUseCase {

    private JavaMailSender javaMailSender;

    public EmailSenderUseCase(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(QuestionDTO question){
        SimpleMailMessage correo = new SimpleMailMessage();
        correo.setTo(question.getUserEmail());
        correo.setFrom("sendemailspringreact@gmail.com");
        correo.setSubject("Respuesta de preguntas");
        correo.setText("Se le ha dado una respuesta a la siguiente pregunta " + question.getQuestion());
        javaMailSender.send(correo);
    }
}
