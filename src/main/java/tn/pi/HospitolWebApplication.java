package tn.pi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.pi.entities.Adherent;
import tn.pi.repositories.AdherentRepository;

import java.time.LocalDate;

@SpringBootApplication
public class HospitolWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(HospitolWebApplication.class, args);
	}
	/*
	@Bean
	public CommandLineRunner init2(AdherentRepository adherentRepository) {
		return args -> {
			Adherent a1 = Adherent.builder()
					.nom_adherent("test")
					.prenom_adherent("aziz")
					.age(23)
					.email("test@test.com")
					.date_inscription(LocalDate.now())
					.build();
			adherentRepository.save(a1);
		};
	}
*/
}
