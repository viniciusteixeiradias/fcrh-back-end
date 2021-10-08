package br.gov.prodemge.seplag.fcrhprova.seguranca;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import br.gov.prodemge.ssc.filter.SscOIDCFilter;

//@Component
@WebFilter(urlPatterns = { "/area-restrita/*", "/privado/*"}, initParams = {
		@WebInitParam(name = "otimizado", value = "true"),
		@WebInitParam(name = "telaDeLogout", value = "logout")})
public class SscFilter extends SscOIDCFilter {

}
