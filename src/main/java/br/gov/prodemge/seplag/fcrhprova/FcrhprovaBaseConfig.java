package br.gov.prodemge.seplag.fcrhprova;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.gov.prodemge.seplag.fcrhprova.negocio.IFcrhprovaBaseFacade;
import br.gov.prodemge.seplag.fcrhprova.negocio.FcrhprovaBaseFacadeImp;
import br.gov.prodigio.comum.ContextParameters;
import br.gov.prodigio.controle.spring.LookupContextListener;

@Configuration
@ServletComponentScan(basePackages = {"br.gov.prodemge.seplag.fcrhprova", "br.gov.prodemge.ssc.servlet"})
@ComponentScan(basePackages = 		 {"br.gov.prodemge.seplag.fcrhprova", "br.gov.prodemge.ssc.servlet", "br.gov.prodigio"} )
public class FcrhprovaBaseConfig implements ServletContextInitializer {
 
	@Autowired
	IFcrhprovaBaseFacade fachadaCustomizada;
	
	@Autowired
	LookupContextListener lookupListener;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setAttribute(ContextParameters.INTERFACE_DE_NEGOCIO, fachadaCustomizada);
		
		lookupListener.inicializaLookupsDaAplicacao(servletContext, "br.gov.prodigio", "br.gov.prodemge.seplag.fcrhprova");
	}

	@Bean
	public IFcrhprovaBaseFacade createFacade() {
		return new FcrhprovaBaseFacadeImp();
	}
	
}
