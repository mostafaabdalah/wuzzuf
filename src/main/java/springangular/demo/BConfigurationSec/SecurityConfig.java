package springangular.demo.BConfigurationSec;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("the user name and password "+new BCryptPasswordEncoder().encode("123"));
        auth.
                    inMemoryAuthentication()
                    .withUser("abdo Nasr").password("{noop}123").roles("USER")
                    .and()
                    .withUser("El-Tarek Ragab").password("{noop}ELT").roles("normal")
                    .and()
                    .withUser("Mustafa Abdallah").password("{noop}Mos-tf"). roles("normal");
    }
}