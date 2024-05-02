package logvinov.testTask.userRestApp.service;

import logvinov.testTask.userRestApp.dto.UserDTO;

import java.util.Date;
import java.util.List;

public interface UserService {
    List<UserDTO> getUsersByDateOfBirthRange(Date dateFrom, Date dateTo);
    UserDTO createUser(UserDTO userDTO);
    UserDTO putUser(Long id, UserDTO userDTO);
    UserDTO patchUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);

}
