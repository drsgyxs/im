package com.drsg.demo.v1.config;

import com.drsg.demo.v1.entity.Result;
import com.drsg.demo.v1.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    public SecurityConfig(TokenAuthenticationFilter tokenAuthenticationFilter) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers("/js/**", "/css/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .formLogin()
                .successHandler(authenticationSuccessHandler())
//                .failureHandler(authenticationFailureHandler())
                .permitAll()
                .and()
                .exceptionHandling()
//                .authenticationEntryPoint(authenticationExceptionHandler())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .headers()
                .cacheControl()
                .disable()
                .and()
                .addFilterBefore(this.tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
//                    httpServletResponse.setContentType("application/json;charset=utf-8");
//                    PrintWriter writer = httpServletResponse.getWriter();
//                    writer.println(new ObjectMapper().writeValueAsString("注销成功"));
//                }).permitAll()
//                .and()
//                .oauth2Login()
//                .loginPage("/login/oauth2")
////                .loginProcessingUrl("/oauth2/authorization/github")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated();
    }


    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            String token = JwtUtils.createToken(authentication);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().println(new ObjectMapper().writeValueAsString(Result.ok(token)));
        };
    }

    private AuthenticationEntryPoint authenticationExceptionHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter writer = httpServletResponse.getWriter();
            String message;
            ObjectMapper objectMapper = new ObjectMapper();
            if (e instanceof InsufficientAuthenticationException) {
                message = objectMapper.writeValueAsString(Result.fail("请登录"));
            } else {
                message = objectMapper.writeValueAsString(Result.fail(e.getMessage()));
            }
            writer.println(message);
        };
    }

    private AuthenticationFailureHandler authenticationFailureHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().println(new ObjectMapper().writeValueAsString(Result.fail("用户名或密码错误")));
        };
    }
}
