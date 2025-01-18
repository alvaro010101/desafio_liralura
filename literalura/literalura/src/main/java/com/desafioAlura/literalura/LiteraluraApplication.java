package com.desafioAlura.literalura;

import com.desafioAlura.literalura.principal.Principal;
import com.desafioAlura.literalura.repository.AutorRepository;
import com.desafioAlura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Autowired
	private LibroRepository repository;
	@Autowired
	private AutorRepository autorRepository;


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(autorRepository,repository);
		principal.muestraElMenu();

	}


}
