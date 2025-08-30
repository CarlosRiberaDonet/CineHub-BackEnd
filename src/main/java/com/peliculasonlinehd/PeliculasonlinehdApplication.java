package com.peliculasonlinehd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class PeliculasonlinehdApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PeliculasonlinehdApplication.class);

		// Obtener puerto desde variable de entorno
		String port = System.getenv("PORT");
		if (port == null) port = "8080"; // fallback local

		Map<String, Object> props = new HashMap<>();
		props.put("server.port", port);
		app.setDefaultProperties(props);

		app.run(args);
	}
}