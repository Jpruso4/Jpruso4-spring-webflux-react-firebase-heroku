package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UserCreateUseCase implements SaveUser {

    private final UserRepository userRepository;
    private final MapperUtils mapperUtils;

    public UserCreateUseCase(UserRepository userRepository, MapperUtils mapperUtils) {
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(UserDTO newUser) {
        return userRepository
                .save(mapperUtils.mapperToUser(null).apply(newUser))
                .map(User::getId);
    }
}
