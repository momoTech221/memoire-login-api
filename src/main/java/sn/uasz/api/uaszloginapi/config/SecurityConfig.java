package sn.uasz.api.uaszloginapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(userAuthenticationEntryPoint))
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/users/login", "/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/getUserByEmail/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/users/setPassword/**").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/users/add/**").hasAuthority("admin")
//                        .requestMatchers(HttpMethod.POST, "/users/addUser").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/users/search/**").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/users/delete/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/users/addUser").authenticated()
//                        .requestMatchers(HttpMethod.GET, "/roles/**").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/roles/**").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/roles/**").permitAll()
                        .anyRequest().authenticated())
        ;
        return http.build();
    }
}
