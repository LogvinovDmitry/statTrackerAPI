package logvinov.testTask.userRestApp.security;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain
    ) throws ServletException, IOException {

//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//        // Проверка публичных маршрутов
//        String requestURI = request.getRequestURI();
//        if (requestURI.startsWith("/api/v1/auth")) {
//            // Пропустить фильтр для разрешенных маршрутов
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
        String bearerToken = ((HttpServletRequest) servletRequest).getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken = bearerToken.substring(7);
        }

        if (bearerToken != null && jwtTokenProvider.isValid(bearerToken)) {
            try {
                Authentication authentication = jwtTokenProvider.getAuthentication(bearerToken);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
            filterChain.doFilter(servletRequest, servletResponse);

    }

}


