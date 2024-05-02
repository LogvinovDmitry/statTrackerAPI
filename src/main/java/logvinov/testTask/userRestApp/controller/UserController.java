package logvinov.testTask.userRestApp.controller;

import logvinov.testTask.userRestApp.dto.UserDTO;
import logvinov.testTask.userRestApp.dto.UserErrorResponse;
import logvinov.testTask.userRestApp.exception.UserNotCreatUpdateException;
import logvinov.testTask.userRestApp.exception.UserNotFoundException;
import logvinov.testTask.userRestApp.service.UserService;
import logvinov.testTask.userRestApp.util.ErrorBuilder;
import logvinov.testTask.userRestApp.util.UserPatchDTOValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserPatchDTOValidator userPatchDTOValidator;

    @GetMapping()
    public List<UserDTO> getUsersByDateOfBirthRange(@RequestParam("dateFrom")
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFrom,
                                                    @RequestParam("dateTo")
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateTo) {

        return userService.getUsersByDateOfBirthRange(dateFrom, dateTo);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO,
                                              BindingResult bindingResult) {

        checkForErrors(bindingResult);
        UserDTO createdUser = userService.createUser(userDTO);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> putUser(@PathVariable("id") Long id, @RequestBody @Valid UserDTO userDTOToUpdate,
                                           BindingResult bindingResult) {

        checkForErrors(bindingResult);
        UserDTO updatedUserDTO = userService.putUser(id, userDTOToUpdate);

        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> patchUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTOToUpdate,
                                             BindingResult bindingResult) {
        userPatchDTOValidator.validate(userDTOToUpdate, bindingResult);
        checkForErrors(bindingResult);
        UserDTO updatedUserDTO = userService.patchUser(id, userDTOToUpdate);

        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "User with this id wasn't found!",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatUpdateException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(IllegalStateException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private static void checkForErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = ErrorBuilder.buildErrors(bindingResult);
            throw new UserNotCreatUpdateException(errors);
        }
    }

}
