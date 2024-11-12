package sn.uasz.api.uaszloginapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

//import sn.uasz.api.uaszloginapi.dtos.UserDto;
import sn.uasz.api.uaszloginapi.entities.LogResponseDto;
import sn.uasz.api.uaszloginapi.entities.RoleDto;
import sn.uasz.api.uaszloginapi.services.impl.UserLogServiceImpl;
import sn.uasz.spi.authApi.model.Log;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {

    @Value("${app.secret-key}")
    private String secretKey;

    private final UserLogServiceImpl userLogService;

    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(Log log) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 18000000); // 5 hour

        sn.uasz.api.uaszloginapi.entities.UserDto user1 = userLogService.findByEmail(log.getLogin());
        List<String> roles = user1.getRoles().stream()
                .map(RoleDto::getName)
                        .toList();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(log.getLogin())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("nom: ", user1.getNom())
                .withClaim("prenom: ", user1.getPrenom())
                .withClaim("roles: ", roles)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        LogResponseDto user = LogResponseDto.builder()
                .login(decoded.getSubject())
                .nom(decoded.getClaim("nom").asString())
                .prenom(decoded.getClaim("prenom").asString())
                .roles(decoded.getClaim("roles").asList(RoleDto.class))
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        List<String> roles = decoded.getClaim("roles").asList(String.class) ;

        sn.uasz.api.uaszloginapi.entities.UserDto user = userLogService.findByEmail(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(user, null, roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }

}
