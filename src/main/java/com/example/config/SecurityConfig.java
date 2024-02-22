package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(
            new AntPathRequestMatcher("/css/**"), 
            new AntPathRequestMatcher("/js/**"), 
            new AntPathRequestMatcher("/img/**"),
            new AntPathRequestMatcher("/login/**"), // ログイン成功後のページへのアクセスを許可します
            new AntPathRequestMatcher("/toInsert/**"),
            new AntPathRequestMatcher("/insert/**"),
            new AntPathRequestMatcher("/"),
            new AntPathRequestMatcher("/employee/**")
        ).permitAll()
        .anyRequest().authenticated())
    .formLogin(formLogin -> formLogin
        .loginPage("/") // ここにカスタムログインページのパスを指定します
        .permitAll());

        return http.build();
    }
}