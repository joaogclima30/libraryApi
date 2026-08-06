package io.github.joaogclima30.libraryapi.config;

import io.github.joaogclima30.libraryapi.Security.CustomUserDetailsService;
import io.github.joaogclima30.libraryapi.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)//Proteção das paginas Html
                .formLogin(configurer -> {
                    configurer.loginPage("/login");
                })
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize ->{

                    /*Existe uma forma melhor de fazer usando @EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
                     e colocando as regras de hasRole no proprio metodo*/
                    authorize.requestMatchers("/login").permitAll();

                    authorize.requestMatchers(HttpMethod.POST,"/autores/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE,"/autores/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/autores/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET, "/autores/**").hasAnyRole("USER", "ADMIN");
                    authorize.requestMatchers("/livros/**").hasAnyRole("USER", "ADMIN");

                    //authorize.anyRequest().authenticated();
                })
                .build();
    }

    //Criptografar uma senha
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

      //Usando userDetails em memoria
    @Bean
    public UserDetailsService userDetailsService(UsuarioService usuarioService){
//
//        UserDetails user1 = User.builder()
//                .username("usuario")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("admin")
//                .password(encoder.encode("321"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);

        return new CustomUserDetailsService(usuarioService);
    }
}