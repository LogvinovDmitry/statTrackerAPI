package logvinov.testTask.userRestApp.service;


import logvinov.testTask.userRestApp.dto.auth.JwtRequest;
import logvinov.testTask.userRestApp.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}