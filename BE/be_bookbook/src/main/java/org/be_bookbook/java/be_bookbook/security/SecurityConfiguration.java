package org.be_bookbook.java.be_bookbook.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/books/create", "/books/edit/**", "/books/delete/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/books/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/genres", "/genres**").hasAnyAuthority("ADMIN")
                .requestMatchers("/books", "/books/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/**").permitAll()
                )
                .formLogin(form -> form
                .defaultSuccessUrl("/books", true)
                .permitAll()
                )
                .logout(logout -> logout .logoutUrl("/logout") // URL per effettuare il logout
                .permitAll() // Permetti l'accesso all'URL di logout )
                )
                .exceptionHandling(exception -> exception 
                .accessDeniedPage("/access-denied")
                );           
        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
