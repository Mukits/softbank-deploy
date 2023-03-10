package edu.dss.softbank.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    private static Logger logger = LogManager.getLogger(SpringSecurity.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
           http.csrf().disable()  // this line disables Cross-Site Request Forgery
                .authorizeRequests((authorize) ->
                        authorize.antMatchers("/register/**").permitAll()
                                .antMatchers("/user/**").hasRole("ADMIN")
                                .antMatchers("/client/**").hasRole("ADMIN")
                                .antMatchers("/index").permitAll()
                                // the following line enables the h2-console - do not publish this into production!
                                //.antMatchers("/").permitAll()
                                //.antMatchers("/h2-console/**").permitAll()
                                .antMatchers("/clients").hasRole("ADMIN")
                                .antMatchers("/users").hasRole("ADMIN")
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/users")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        //http.headers().frameOptions().disable();  // this line is only used for the h2-console as well - do not publish to production!
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        }
}
