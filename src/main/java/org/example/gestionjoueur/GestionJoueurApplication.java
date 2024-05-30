package org.example.gestionjoueur;

import org.example.gestionjoueur.repository.JoueurRepository;
import org.example.gestionjoueur.security.entities.Joueur;
import org.example.gestionjoueur.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class GestionJoueurApplication implements CommandLineRunner{

	@Autowired
	private JoueurRepository joueurRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionJoueurApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		joueurRepository.save(new Joueur(null,"adnane",new Date(),false,55));
//		joueurRepository.save(new Joueur(null,"moe",new Date(),false,885));
//		joueurRepository.save(new Joueur(null,"meryem",new Date(),true,102));

	}


//	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
//		//PasswordEncoder passwordEncoder= passwordEncoder();
//
//		return args -> {
//
//			UserDetails u1= jdbcUserDetailsManager.loadUserByUsername("user11");
//			if (u1==null)  jdbcUserDetailsManager.createUser(
//					User.withUsername("user11").password(passwordEncoder.encode("12345678")).roles("USER").build()
//			);
//
//
//			UserDetails u2= jdbcUserDetailsManager.loadUserByUsername("user22");
//
//			if (u2==null)   jdbcUserDetailsManager.createUser(
//					User.withUsername("user22").password(passwordEncoder.encode("12345678")).roles("ADMIN").build()
//			);
//
//
//			UserDetails u3= jdbcUserDetailsManager.loadUserByUsername("user33");
//
//			if (u3==null)  jdbcUserDetailsManager.createUser(
//					User.withUsername("user33").password(passwordEncoder.encode("12345678")).roles("USER","ADMIN").build()
//			);
//
//		};
//	}


//	//@Bean
//	CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
//		return args -> {
//			accountService.addNewRole("USER");
//			accountService.addNewRole("ADMIN");
//			accountService.addNewUser("adnane","1234","adnane@gmail.com","1234");
//			accountService.addNewUser("meryem","1234","meryem@gmail.com","1234");
//			accountService.addRoleToUser("adnane","USER");
//			accountService.addRoleToUser("adnane","ADMIN");
//			accountService.addRoleToUser("meryem","USER");
//
//		};
//	}

//	@Bean
//	PasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}

	
}
