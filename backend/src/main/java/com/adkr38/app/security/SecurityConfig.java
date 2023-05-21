package com.adkr38.app.security;
import com.adkr38.app.filters.CustomAuthenticationFilter;
import com.adkr38.app.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * This is the main configuration class for security in the application. It enables web security,
 * sets up the password encoder, and sets up the security filter chain.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // UserDetailsService is an interface provided by Spring Security that defines a way to retrieve user information
    @Autowired
    private UserDetailsService userDetailsService;


    // Autowired instance of the AuthenticationManagerBuilder
    @Autowired
    private AuthenticationManagerBuilder authManagerBuilder;

    /**
     * Bean definition for PasswordEncoder
     *
     * @return an instance of the DelegatingPasswordEncoder
     */
    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Bean definition for AuthenticationManager
     *
     * @param authenticationConfiguration the instance of AuthenticationConfiguration
     * @return an instance of the AuthenticationManager
     * @throws Exception if there is an issue getting the instance of the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /**
     * Bean definition for SecurityFilterChain
     *
     * @param http the instance of HttpSecurity
     * @return an instance of the SecurityFilterChain
     * @throws Exception if there is an issue building the SecurityFilterChain
     */
    @Bean
    @CrossOrigin(origins = "http://localhost:5173") 
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CustomAuthenticationFilter instance created
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());
        // set the URL that the filter should process
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        // disable CSRF protection
        http.csrf().disable();
        // set the session creation policy to stateless
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        // set up authorization for different request matchers and user roles
        // Configure CORS
        CorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:5173");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/api/**", corsConfig);
        http.cors().configurationSource(source);

        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/login/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/validate").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/appointments").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/users").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .anyRequest().authenticated()
        );
        // add the custom authentication filter to the http security object
        http.addFilter(customAuthenticationFilter);
        // Add the custom authorization filter before the standard authentication filter.
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Build the security filter chain to be returned.
        return http.build();
    }
}
