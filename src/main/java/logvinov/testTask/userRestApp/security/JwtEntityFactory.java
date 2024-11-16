package logvinov.testTask.userRestApp.security;

import logvinov.testTask.userRestApp.model.user.Role;
import logvinov.testTask.userRestApp.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtEntityFactory {

    public static JwtEntity create(final User user) {
        JwtEntity jwtEntity = new JwtEntity(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
int f = 9;
        return jwtEntity;
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(final List<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}