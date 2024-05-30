package org.example.gestionjoueur.security;

import org.example.gestionjoueur.security.repo.AppUserRepository;
import org.example.gestionjoueur.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    AppUserRepository appUserRepository;

/*

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeRequests((requests) -> requests
                        .requestMatchers(
                                "/user/index", "/user/", "/admin/delete","/admin/editJoueur",
                                "/admin/formJoueurs"
                        ).authenticated()
                        .requestMatchers(
                                "/user/index", "/", "/webjars/**"
                        ).permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        //.defaultSuccessUrl("/user/index", true)
                        .permitAll()
                );
        System.out.println("done");
        return http.build();
    }
*/



    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeRequests((requests) -> requests
                        .requestMatchers(
                                "/ajouter", "/ajouterOnce", "/ajouterproduit",
                                "/deleteProduit", "/editProduit"
                        ).authenticated()
                        .requestMatchers(
                                "/indexpage", "/", "/webjars/**"
                        ).permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/user/index", true)
                        .permitAll()
                );
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            Optional<User> user = appUserRepository.findByUsername(username);
//            return user.map(u -> org.springframework.security.core.userdetails.User
//                            .withUsername(u.getUsername())
//                            .password(u.getPassword())
//                            .authorities("ROLE_" + u.getUserRole()) // Ensure roles are prefixed with "ROLE_"
//                            .build())
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//        };
//    }



    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails adminmanager = User.withUsername("adminmanager")
                .password("12345")
                .roles("managetable", "delete", "update")
                //.roles("admin")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("12345")
                //.authorities("admin")
                .roles("admin")
                .build();
        UserDetails user = User.withUsername("user")
                .password("12345")
                .roles("USER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(adminmanager, admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

   /* @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}
