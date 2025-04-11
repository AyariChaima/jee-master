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
}
