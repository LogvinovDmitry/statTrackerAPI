package logvinov.testTask.userRestApp.controller;


import logvinov.testTask.userRestApp.dto.UserDTO;
import logvinov.testTask.userRestApp.dto.auth.JwtRequest;
import logvinov.testTask.userRestApp.dto.auth.JwtResponse;
import logvinov.testTask.userRestApp.service.AuthService;
import logvinov.testTask.userRestApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody final JwtRequest loginRequest) {

        int f = 5;
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDTO register(@Validated @RequestBody final UserDTO userDTO) {
        int f = 5;
        return userService.createUser(userDTO);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody final String refreshToken) {

        return authService.refresh(refreshToken);
    }
}