package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UserUpdateUseCase implements UpdateUser{

    private final UserRepository userRepository;
    private final MapperUtils mapperUtils;

    public UserUpdateUseCase(UserRepository userRepository, MapperUtils mapperUtils) {
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<UserDTO> apply(UserDTO userDTO) {
        Objects.requireNonNull(userDTO.getId(), "Id of the user is required");
        return userRepository.save(mapperUtils.mapperToUser(userDTO.getId()).apply(userDTO))
                .map(mapperUtils.mapEntityToUser());
    }
}
