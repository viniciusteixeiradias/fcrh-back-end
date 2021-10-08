package br.gov.prodemge.seplag.fcrhprova.controle.casousopublico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prodemge.seplag.fcrhprova.negocio.IFcrhprovaBaseFacade;
import br.gov.prodemge.ssc.admin.entidades.usuario.UsuarioVO;

@RestController
public class RestCtr {
	
	
	@Autowired
	IFcrhprovaBaseFacade fachada;
	
	
	@GetMapping("/oi")
	public UsuarioVO oi() {
		
		UsuarioVO usuario = new UsuarioVO();
		usuario.setNome("oi");
		return usuario;
	}

}
