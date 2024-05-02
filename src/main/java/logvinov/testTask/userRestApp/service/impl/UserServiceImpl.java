package logvinov.testTask.userRestApp.service.impl;

import logvinov.testTask.userRestApp.dto.UserDTO;
import logvinov.testTask.userRestApp.exception.UserNotCreatUpdateException;
import logvinov.testTask.userRestApp.exception.UserNotFoundException;
import logvinov.testTask.userRestApp.model.User;
import logvinov.testTask.userRestApp.repository.UserRepository;
import logvinov.testTask.userRestApp.service.UserService;
import logvinov.testTask.userRestApp.util.AgeCalculator;
import logvinov.testTask.userRestApp.util.UserConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Value("${user.min.age}")
    private int defaultAge;

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }



    @Override
    public List<UserDTO> getUsersByDateOfBirthRange(Date dateFrom, Date dateTo) {

        if (!dateFrom.before(dateTo)) {
            throw new IllegalStateException("The date range must be specified from the earliest date to the" +
                    "latest date");
        }

        List<User> userByDateRange = userRepository.findUsersByBirthDateBetween(dateFrom, dateTo);

        return userByDateRange.stream()
                .map(user -> userConverter.convertToUserDTO(user))
                .collect(Collectors.toList());

    }

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        int userAge = AgeCalculator.calculateAge(userDTO.getBirthDate());
        if (userAge < defaultAge) {
            throw new UserNotCreatUpdateException(String.format("User must be at least %s years old", defaultAge));
        }

        User user = userConverter.convertToUser(userDTO);
        User saveUser = userRepository.save(user);
        return userConverter.convertToUserDTO(saveUser);

    }

    @Transactional
    @Override
    public UserDTO putUser(Long id, UserDTO userDTOToUpdate) {

        userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (AgeCalculator.calculateAge(userDTOToUpdate.getBirthDate()) < defaultAge) {

            throw new UserNotCreatUpdateException(String.format("User must be at least %s years old", defaultAge));
        }

        userDTOToUpdate.setId(id);
        User updatedUser = userRepository.save(userConverter.convertToUser(userDTOToUpdate));
        return userConverter.convertToUserDTO(updatedUser);

    }

    @Transactional
    @Override
    public UserDTO patchUser(Long id, UserDTO userDTOToUpdate) {

        User userToUpdate = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (userDTOToUpdate.getBirthDate() != null && AgeCalculator.calculateAge(userDTOToUpdate.getBirthDate()) < defaultAge) {

            throw new UserNotCreatUpdateException(String.format("User must be at least %s years old", defaultAge));
        }

        userConverter.updateUserFromDTO(userDTOToUpdate, userToUpdate);
        User updatedUser = userRepository.save(userToUpdate);
        return userConverter.convertToUserDTO(updatedUser);

    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }
}







