package logvinov.testTask.userRestApp.service.impl;

import logvinov.testTask.userRestApp.dto.UserDTO;
import logvinov.testTask.userRestApp.exception.UserNotCreatUpdateException;
import logvinov.testTask.userRestApp.exception.UserNotFoundException;
import logvinov.testTask.userRestApp.model.user.User;
import logvinov.testTask.userRestApp.repository.UserRepository;
import logvinov.testTask.userRestApp.service.UserService;
import logvinov.testTask.userRestApp.util.AgeCalculator;
import logvinov.testTask.userRestApp.util.UserConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserDTO getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return userConverter.convertToUserDTO(user);
    }
    @Override
    public User getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
    @Override
    public User getUserEntityById(String id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
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
    public UserDTO putUser(String id, UserDTO userDTOToUpdate) {

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
    public UserDTO patchUser(String id, UserDTO userDTOToUpdate) {

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
    public void deleteUser(String id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }
}







