package ro.itschool.demospringdata.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ro.itschool.demospringdata.security.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig {


    @Bean
    public CustomUserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    //@Autowired
    //private CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService) ; //verifica daca exista user-ul in DB
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //daca vrem sesiune sau nu
        //ce endpoint-uri vrem sa fie autentificate,
        //ce endpoint-uri nu vrem sa fie autentificate
        //de ce roluri are nevoie un user pt a accesa un endpoint
        //mecanismul de autentificare - basic authentication
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/students/**").hasRole("MODERATOR")
                .antMatchers("/api/test/all").permitAll()
                .antMatchers("/api/test/user").hasAnyRole("BASIC_USER","MODERATOR")
                .antMatchers("/api/test/admin").hasRole("ADMIN")
                .and()
                .httpBasic();

        return http.build();

    }


}
