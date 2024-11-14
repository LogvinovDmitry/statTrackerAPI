package logvinov.testTask.userRestApp.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import logvinov.testTask.userRestApp.dto.auth.JwtResponse;
import logvinov.testTask.userRestApp.exception.AccessDeniedException;
import logvinov.testTask.userRestApp.model.user.Role;
import logvinov.testTask.userRestApp.model.user.User;
import logvinov.testTask.userRestApp.service.UserService;
import logvinov.testTask.userRestApp.service.props.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(
            final String userId,
            final String email,
            final String firstName,
            final String lastName,
            final Set<Role> roles
    ) {
        Claims claims = Jwts.claims()
                .subject(email)
                .add("id", userId)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("roles", resolveRoles(roles))
                .build();

        Instant validity = Instant.now().plus(jwtProperties.getAccess(), ChronoUnit.HOURS);

        return Jwts.builder()
                .claims(claims)
                .expiration(Date.from(validity))
                .signWith(key)
                .compact();
    }

    private List<String> resolveRoles(final Set<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public String createRefreshToken(
            final String userId,
            final String email
    ) {
        Claims claims = Jwts.claims()
                .subject(email)
                .add("id", userId)
                .build();
        Instant validity = Instant.now()
                .plus(jwtProperties.getRefresh(), ChronoUnit.DAYS);
        return Jwts.builder()
                .claims(claims)
                .expiration(Date.from(validity))
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshUserTokens(final String refreshToken) {
        JwtResponse jwtResponse = new JwtResponse();
        if (!isValid(refreshToken)) {
            throw new AccessDeniedException();
        }
        String userId = String.valueOf(getId(refreshToken));
        User user = userService.getUserEntityById(userId);
        jwtResponse.setId(userId);
        jwtResponse.setEmail(user.getEmail());
        jwtResponse.setAccessToken(
                createAccessToken(userId, user.getEmail(), user.getFirstName(), user.getLastName(), user.getRoles()));
        jwtResponse.setRefreshToken(
                createRefreshToken(userId, user.getEmail()));
        return jwtResponse;
    }

    public boolean isValid(final String token) {
        Jws<Claims> claims = Jwts
                .parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token);

        return claims.getPayload()
                .getExpiration()
                .after(new Date());
    }

    private String getId(final String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", String.class);
    }

    private String getUserEmail (final String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Authentication getAuthentication(final String token) {
        String email = getUserEmail(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                "",
                userDetails.getAuthorities()
        );
    }

}
