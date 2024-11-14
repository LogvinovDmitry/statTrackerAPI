package logvinov.testTask.userRestApp.util;

import logvinov.testTask.userRestApp.dto.UserDTO;
import logvinov.testTask.userRestApp.model.user.User;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserConverter {

    private ModelMapper modelMapper;

    @Autowired
    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public void updateUserFromDTO(UserDTO userDTO, User user) {
        modelMapper.map(userDTO, user);

    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
