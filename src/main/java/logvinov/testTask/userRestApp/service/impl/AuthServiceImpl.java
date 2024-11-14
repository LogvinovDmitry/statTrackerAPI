package logvinov.testTask.userRestApp.service.impl;

import logvinov.testTask.userRestApp.dto.auth.JwtRequest;
import logvinov.testTask.userRestApp.dto.auth.JwtResponse;
import logvinov.testTask.userRestApp.model.user.User;
import logvinov.testTask.userRestApp.security.JwtTokenProvider;
import logvinov.testTask.userRestApp.service.AuthService;
import logvinov.testTask.userRestApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {

        JwtResponse jwtResponse = new JwtResponse();
        //На этом шаге вызывается public class JwtUserDetailsService implements UserDetailsService {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        User user = userService.getUserEntityByEmail(loginRequest.getEmail());

        jwtResponse.setId(user.getId());
        jwtResponse.setEmail(user.getEmail());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(
                user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getRoles())
        );
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getEmail()));

        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(final String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
