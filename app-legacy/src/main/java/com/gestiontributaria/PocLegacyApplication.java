package com.gestiontributaria;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PocLegacyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocLegacyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			System.out.println("==========================================");
			System.out.println(" EJECUTANDO PRUEBA DE CONCEPTO (PoC)");
			System.out.println("==========================================");

			// Simulamos valorar el inmueble 101
			FormularioValoracion form = new FormularioValoracion(101);
			form.ejecutarCicloDeVida();

			System.out.println("==========================================");
		};
	}
}