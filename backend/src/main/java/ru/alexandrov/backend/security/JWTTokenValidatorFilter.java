package ru.alexandrov.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.alexandrov.backend.constants.ProjectConstants;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = request.getHeader(ProjectConstants.JWT_HEADER);
        if (null != jwt) {
            try {
                //Создаём ключ
                SecretKey key = Keys.hmacShaKeyFor(
                        ProjectConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                //Достаём email и роль из токена
                String email = String.valueOf(claims.get(ProjectConstants.EMAIL));
                String role = String.valueOf(claims.get(ProjectConstants.ROLE));

                Authentication auth = new UsernamePasswordAuthenticationToken(email, null,
                        Collections.singleton(new SimpleGrantedAuthority(role)));

                //Сохраняем результат прохождения аутентификации в ContextHolder
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception ex) {
                //Если jwt неправильный, будет выброшено исключение. Отправляем 401.
                response.addHeader("Content-Type", "application/json;charset=UTF-8");
                response.getOutputStream().print(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        //Не проводим аутентифкацию если путь текущего запроса совпадает с одним из WHITE_LIST
        return Arrays.stream(ProjectConstants.WHITE_LIST[0]).anyMatch(str -> request.getServletPath().matches(str));
    }
}

