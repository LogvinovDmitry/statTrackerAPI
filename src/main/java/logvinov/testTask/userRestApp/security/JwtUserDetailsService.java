package logvinov.testTask.userRestApp.security;

import logvinov.testTask.userRestApp.model.user.User;
import logvinov.testTask.userRestApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String email) {

        User user = userService.getUserEntityByEmail(email);
        return JwtEntityFactory.create(user);
    }
}