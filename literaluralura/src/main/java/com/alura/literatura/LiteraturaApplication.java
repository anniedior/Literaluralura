package com.alura.literatura;

import com.alura.literatura.principal.principal.Principal;
import com.alura.literatura.service.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LibroServicio servicio;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(servicio);
		principal.ejecutable();
	}
}
