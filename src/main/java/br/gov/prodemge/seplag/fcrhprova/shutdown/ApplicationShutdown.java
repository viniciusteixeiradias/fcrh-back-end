package br.gov.prodemge.seplag.fcrhprova.shutdown;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationShutdown implements ApplicationListener<ContextClosedEvent> {

	public final static Logger log = LoggerFactory.getLogger(ApplicationShutdown.class);

	private static RestTemplate restTemplate = new RestTemplate();

	// @Autowired ... //various components and services

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		log.warn("Encerrando componentes necessarios.");
		log.warn("Encerrando conexao com banco.");
		log.warn("Encerrando conexao com barramento.");
		log.warn("Notificando serviços de monitoramento.");
		log.warn("Aplicacao encerrada com sucesso.");
		log.warn("Encerrando Broker.");
	}

	public static void verificaShutdown(String[] args) {
		Boolean respostaHealth = false;
		String port = System.getProperty("env.server.port");
		log.info(port);
		// for(String arg:args) {
		// log.info(arg);
		// String[] param = arg.split("=");
		// if(param.length == 2) {
		// if(param[0].equals("-Denv.server.port")) {
		// port = param[1];
		// //log.info("PORTA " + port);
		// }
		// }
		// }
		try {
			String urlHealth = "http://127.0.0.1:" + port + "/fcrh-prova/actuator/health";
			log.info("URL HEALTH: " + urlHealth);
			ResponseEntity<Map<String, Object>> entity = asMapEntity(

					restTemplate.getForEntity(urlHealth, null, Map.class));
			if ((entity.getStatusCode()).equals(HttpStatus.OK)) {
				log.warn("Aplicacao ativa - iniciando shutdown");
				respostaHealth = true;
			} else {
				log.warn("Aplicacao pronta pra inicializar");
				respostaHealth = false;
			}
		} catch (Exception e) {
		}

		try {
			if (respostaHealth) {
				String urlShutdown = "http://127.0.0.1:" + port + "/fcrh-prova/actuator/shutdown";
				log.info("URL SHUTDONW: " + urlShutdown);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>(null, headers);
				ResponseEntity<Map<String, Object>> entityShutdown = asMapEntity(restTemplate.postForEntity(urlShutdown, entity, Map.class));
				if ((entityShutdown.getStatusCode()).equals(HttpStatus.OK)) {
					log.warn("Aplicacao ativa - shutdown solicitado");
					if ((((String) entityShutdown.getBody().get("message"))).contains("Shutting down")) {
						log.warn("Aplicacao ativa encerrada com sucesso");
					}
					Thread.sleep(2000);
				}
			}
		} catch (Exception e) {
			log.warn("###Falha ao encerrar aplicacao");
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static <K, V> ResponseEntity<Map<K, V>> asMapEntity(ResponseEntity<Map> entity) {
		return (ResponseEntity) entity;
	}

}
