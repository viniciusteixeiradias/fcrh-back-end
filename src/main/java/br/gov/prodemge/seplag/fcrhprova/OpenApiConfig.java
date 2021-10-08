package br.gov.prodemge.seplag.fcrhprova;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Value("${br.gov.prodemge.ambiente}")
	private String ambiente;
	
	@Value("${info.app.versao}")
	private String appVersion;
	
	@Value("${info.java.version}") 
	private String infoJavaVersion;
	
    @Value("${server.servlet.context-path}") 
    private String contextPath;

    @Value("${server.port}") 
    private String serverPort;
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url(contextPath))
                .info(new Info()
                        .title(contextPath)
                        .description(ambiente + " - " + serverPort + " - " + infoJavaVersion)
                        .version(appVersion));
    }
}
