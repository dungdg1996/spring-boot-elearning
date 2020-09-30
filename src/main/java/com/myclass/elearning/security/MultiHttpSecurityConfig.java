package com.myclass.elearning.security;

import com.myclass.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Order(0)
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Configuration
public class MultiHttpSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final UserDetailsService userDetailsService;

    public MultiHttpSecurityConfig(@Qualifier("userDetailsServiceImp") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Order(2)
    @Configuration
    public static class SecurityAdminConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private UserService userService;
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors();
            http.csrf().disable()
                    .antMatcher("/admin/**")
                    .authorizeRequests()
                    .antMatchers(HttpMethod.PUT, "admin/roles")
                    .hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "admin/roles")
                    .hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "admin/roles")
                    .hasRole("ADMIN")
                    .anyRequest()
                    .hasAnyRole("ADMIN", "TEACHER")
                    .and()
                    .formLogin()
                    .loginPage("/admin/login")
                    .loginProcessingUrl("/admin/login")
                    .usernameParameter("email")
                    .failureUrl("/admin/login/error")
                    .successHandler((request, response, authentication) -> {
                        response.sendRedirect("/admin/course");
                    })
                    .permitAll().and()
                    .logout()
                    .permitAll().and()
                    .addFilter(new AdminFilter(authenticationManager(), userService))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        }
    }

    @Order(3)
    @Configuration
    public static class SecurityApiConfig extends WebSecurityConfigurerAdapter {
        private JwtTokenProvider tokenProvider;

        @Autowired
        public void setTokenProvider(JwtTokenProvider tokenProvider) {
            this.tokenProvider = tokenProvider;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors();
            http.csrf().disable()
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/auth/login")
                    .permitAll()
                    .antMatchers(HttpMethod.GET,
                            "/api/categories/**",
                            "/api/courses/**",
                            "/api/targets/**",
                            "/api/videos/**").permitAll()
                    .antMatchers(HttpMethod.GET, "api/role/**")
                    .hasAnyRole("ADMIN", "TEACHER")
                    .antMatchers(HttpMethod.PUT, "api/roles")
                    .hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "api/roles")
                    .hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "api/roles/**")
                    .hasRole("ADMIN")
                    .antMatchers(
                            "/api/courses/**",
                            "/api/targets/**",
                            "/api/videos/**",
                            "/api/roles/**")
                    .hasAnyRole("ADMIN", "TEACHER")
                    .and()
                    .addFilter(new JwtFilter(authenticationManager(), tokenProvider))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }
}
