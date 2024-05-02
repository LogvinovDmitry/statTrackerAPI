package logvinov.testTask.userRestApp.service.impl;

import logvinov.testTask.userRestApp.dto.UserDTO;
import logvinov.testTask.userRestApp.exception.UserNotCreatUpdateException;
import logvinov.testTask.userRestApp.exception.UserNotFoundException;
import logvinov.testTask.userRestApp.model.User;
import logvinov.testTask.userRestApp.repository.UserRepository;
import logvinov.testTask.userRestApp.util.UserConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        UserConverter userConverter = new UserConverter();

        userConverter.setModelMapper(modelMapper);

        userService = new UserServiceImpl(userRepository, userConverter);

        user = new User(1L, "john.doe@example.com", "John", "Doe", new Date(90, 0, 15), "123 Main St", "555-123-4567");
        userDTO = new UserDTO(1L, "john.doe@example.com", "John", "Doe", new Date(90, 0, 15), "123 Main St", "555-123-4567");

    }

    @Test
    void getUsersByDateRange_successfully() {
        Date dateFrom = new Date(75, 0, 1);
        Date dateTo = new Date(99, 11, 31);

        User user1 = new User(1L, "john.doe@example.com", "John", "Doe", new Date(90, 0, 15), "123 Main St", "555-123-4567");
        User user2 = new User(2L, "mary.jane@example.com", "Mary", "Jane", new Date(85, 6, 20), "456 Oak St", "555-987-6543");
        User user3 = new User(3L, "bob.smith@example.com", "Bob", "Smith", new Date(82, 2, 10), "789 Pine St", "555-222-3333");
        User user4 = new User(4L, "susan.jackson@example.com", "Susan", "Jackson", new Date(78, 10, 25), "321 Maple St", "555-444-5555");
        User user5 = new User(5L, "david.wilson@example.com", "David", "Wilson", new Date(98, 8, 5), "555 Cedar St", "555-678-9101");

        List<User> usersInRange = Arrays.asList(user1, user2, user3, user4, user5);

        when(userRepository.findUsersByBirthDateBetween(dateFrom, dateTo)).thenReturn(usersInRange);

        List<UserDTO> result = userService.getUsersByDateOfBirthRange(dateFrom, dateTo);

        verify(userRepository, times(1)).findUsersByBirthDateBetween(dateFrom, dateTo);

        assertEquals(5, result.size());

        for (UserDTO userDTO : result) {
            int dateIsFrom = userDTO.getBirthDate().compareTo(dateFrom);
            assertEquals(1, dateIsFrom);

            int dateIsTo = userDTO.getBirthDate().compareTo(dateTo);
            assertEquals(-1, dateIsTo);

        }

    }

    @Test
    void getUsersByDateRange_invalidDates() {
        Date dateFrom = new Date(2025, 0, 1);
        Date dateTo = new Date(2024, 11, 31);

        assertThrows(IllegalStateException.class, () -> userService.getUsersByDateOfBirthRange(dateFrom, dateTo));
        verify(userRepository, never()).findUsersByBirthDateBetween(dateFrom, dateTo);
    }

    @Test
    void createUser_successfully() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO saveUserDTO = userService.createUser(userDTO);

        verify(userRepository, times(1)).save(any(User.class));
        assertNotNull(saveUserDTO);
        assertEquals(user.getId(), saveUserDTO.getId());
    }

    @Test
    void createUser_invalidUserBirthDate() {
        UserDTO userDTOInvalidUserBirthDate = new UserDTO(1L, "john.doe@example.com", "John", "Doe", new Date(2010, 0, 15), "123 Main St", "555-123-4567");

        assertThrows(UserNotCreatUpdateException.class, () -> userService.createUser(userDTOInvalidUserBirthDate));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void putUser_successfully() {
        Long id = 1L;

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDTO saveUserDTO = userService.putUser(id, userDTO);

        verify(userRepository, times(1)).save(any(User.class));
        assertNotNull(saveUserDTO);
        assertEquals(id, saveUserDTO.getId());
    }

    @Test
    void putUser_userNotFound() {
        Long id = 1L;

        when(userRepository.findById(id)).thenThrow(new UserNotFoundException());

        assertThrows(UserNotFoundException.class, () -> userService.putUser(id, userDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void putUser_invalidUserBirthDate() {
        UserDTO userDTOInvalidUserBirthDate = new UserDTO(1L, "john.doe@example.com", "John", "Doe", new Date(2020, 0, 15), "123 Main St", "555-123-4567");

        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        assertThrows(UserNotCreatUpdateException.class, () -> userService.putUser(id, userDTOInvalidUserBirthDate));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void patchUser_successfully() {
        Long id = 1L;

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDTO saveUserDTO = userService.patchUser(id, userDTO);

        verify(userRepository, times(1)).save(any(User.class));
        assertNotNull(saveUserDTO);
        assertEquals(id, saveUserDTO.getId());
    }

    @Test
    void patchUser_fieldsAreNull() {
        User user = new User(1L, "john.doe@example.com", "John", "Doe", new Date(90, 0, 15), "123 Main St", "555-123-4567");
        UserDTO userDTO = new UserDTO(1L, null, "David", "Doe", new Date(90, 0, 15), "123 Main St", "555-123-4567");

        Long id = 1L;

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDTO saveUserDTO = userService.patchUser(id, userDTO);

        verify(userRepository, times(1)).save(any(User.class));
        assertNotNull(saveUserDTO);
        assertEquals(id, saveUserDTO.getId());
        assertEquals("David", saveUserDTO.getFirstName());
        assertEquals("john.doe@example.com", saveUserDTO.getEmail());
    }

    @Test
    void patchUser_userNotFound() {
        Long id = 1L;

        when(userRepository.findById(id)).thenThrow(new UserNotFoundException());

        assertThrows(UserNotFoundException.class, () -> userService.patchUser(id, userDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void patchUser_invalidUserBirthDate() {
        User user = new User(1L, "john.doe@example.com", "John", "Doe", new Date(90, 0, 15), "123 Main St", "555-123-4567");
        UserDTO userDTO = new UserDTO(1L, "john.doe@example.com", "John", "Doe", new Date(2020, 0, 15), "123 Main St", "555-123-4567");

        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        assertThrows(UserNotCreatUpdateException.class, () -> userService.patchUser(id, userDTO));
    }

    @Test
    void deleteUser_successfully() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.deleteUser(id);

        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteUser_userNotFound() {
        Long id = 1L;

        when(userRepository.findById(id)).thenThrow(new UserNotFoundException());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(id));
        verify(userRepository, never()).deleteById(anyLong());
    }

}