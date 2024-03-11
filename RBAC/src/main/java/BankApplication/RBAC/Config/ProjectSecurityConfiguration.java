package BankApplication.RBAC.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration

public class ProjectSecurityConfiguration {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf((csrf) -> csrf.disable()) // it will disabled permission for post and put

                /*  httpSecurity*/ .authorizeHttpRequests((requests)->requests.
                requestMatchers("/get").hasAnyAuthority("user","admin") // for add users both the user and
                .requestMatchers("getCustomerDetails").hasAnyAuthority("user","admin")
                .requestMatchers("/updateDetails","/deleteDetails").hasAuthority("admin")

                .requestMatchers("/adding","/register").permitAll());
        httpSecurity.httpBasic(withDefaults());
        httpSecurity.formLogin(withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

}
