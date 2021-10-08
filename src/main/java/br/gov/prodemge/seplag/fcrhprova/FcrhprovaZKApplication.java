package br.gov.prodemge.seplag.fcrhprova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class FcrhprovaZKApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(FcrhprovaZKApplication.class);
	}

	public static void main(String[] args) {
		configurarVariaveisDeSistema();
		SpringApplication.run(FcrhprovaZKApplication.class);
	}

	private static void configurarVariaveisDeSistema() {
		System.setProperty("java.locale.providers", "COMPAT,CLDR");
		System.setProperty("prodigio.arquivo.configuracao","fcrh-prova-configuracao.xml");
	}

	@GetMapping("/area-restrita")
	public String indexPrivado() {
		return "/privado/br/gov/prodemge/seplag/fcrhprova/visao/index";
	}

	@GetMapping("/logout")
	public String logout() {
		return "/publico/br/gov/prodemge/seplag/fcrhprova/visao/timeout";
	}
}
