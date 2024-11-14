package logvinov.testTask.userRestApp.service;

import logvinov.testTask.userRestApp.dto.UserDTO;
import logvinov.testTask.userRestApp.model.user.User;

public interface UserService {
    User getUserEntityById(String id);
    UserDTO getUserByEmail(String email);
    User getUserEntityByEmail(String email);
    UserDTO createUser(UserDTO userDTO);
    UserDTO putUser(String id, UserDTO userDTO);
    UserDTO patchUser(String id, UserDTO userDTO);
    void deleteUser(String id);
}