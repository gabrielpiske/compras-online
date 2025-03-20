package com.julioepiske.appLojaOnline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests(auth -> auth
                .requestMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login") // rota da pagina de login
            .defaultSuccessUrl("/home", true)
            .permitAll()
        )
        .logout(logout -> logout
                        .logoutUrl("/logout") // url de logout
                        .logoutSuccessUrl("/login") // manda pra login depois do logout
                        .permitAll()
        )
        .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
