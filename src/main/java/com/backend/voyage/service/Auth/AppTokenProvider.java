package com.backend.voyage.service.Auth;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AppTokenProvider extends OncePerRequestFilter implements AuthenticationEntryPoint {

    private static final String expiracao = "86400000";
    private static final String SECRET = "voyage6654f";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {

        String jwt = getTokenFromRequest(request);

        if (nonNull(jwt) && tokenValid(jwt)) {

            autenticarUsuario();
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader(AUTHORIZATION);

        if (nonNull(token)) {

            return token;
        }

        return null;
    }

    private Boolean tokenValid(String token) {

        try {

           Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
           return true;

        } catch (Exception e) {

            return false;
        }
    }

    private void autenticarUsuario() {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(null, null, null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public static String generateToken(String userName) {

        Date hj = new Date();
        Date dateExpiracao = new Date(hj.getTime() + Long.parseLong(expiracao));

        return Jwts
                .builder()
                .setSubject(userName)
                .setExpiration(dateExpiracao)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static void main(String[] args) {

        String asd  = generateToken("asd");
        System.out.println("asd");
    }
}
