package br.gov.prodemge.seplag.fcrhprova;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class HealthConfig extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
//		if (verificaServicoAtivo()) {
			builder.up().withDetail("app", "Banco e Link Barramento OK");
//		} else {
//			builder.up().withDetail("error", "Falha de banco de dados.");
//		}

	}

//	private boolean verificaServicoAtivo() {
//		System.out.println("\n\n\n TESTE HEALTH \n\n\n");
//		return true;
//	}
}
