package org.example.app.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger log = Logger.getLogger(AppSecurityConfig.class);

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("config in memory auth user");
        auth
                .inMemoryAuthentication()
                .withUser("1")
                .password(passwordEncoder().encode("1"))
                .roles("USER");
        log.debug("****After config in memory auth");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("config http security");
        http.headers().frameOptions().disable(); //Позволит интерфейсу базы данных рендерится корректно
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login*").permitAll() // Разрешаем все начинающееся с /login
                .anyRequest().authenticated() // Все реквесты должны проходить аутентификацию
                .and()
                .formLogin()
                .loginPage("/login") // Предоставляем собственную форму логина
                .loginProcessingUrl("/login/auth") // Процесс, который обрабатывает аутентификацию
                .defaultSuccessUrl("/books/shelf", true) // Страница по умолчанию в случае успешного логина
                .failureUrl("/login"); // Страница в случае ошибки
        log.debug("****After config http security");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        log.info("config web security");
        web
                .ignoring() // игнорировать запросы, которые начинаются с
                .antMatchers("/images/**");
    }
}
