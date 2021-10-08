package br.gov.prodemge.seplag.fcrhprova.controle;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

import br.gov.prodemge.ssc.comuns.constantes.Constantes;
import br.gov.prodigio.controle.WindowPrincipalUnsecured;


public class FcrhprovaPublicoWindowIntroducao extends WindowPrincipalUnsecured {
	
	
	public void signIn() {
	    Executions.getCurrent().getSession().setAttribute(Constantes.UNIDADE_AUTENTICADA, null);
	    Executions.getCurrent().getSession().setAttribute(Constantes.USUARIO_AUTENTICADO, null);
	    Executions.getCurrent().getSession().setAttribute(Constantes.UNIDADES_CARREGADAS, null);
	    // Redirecionar para index da parte privada
	    Executions.sendRedirect("/privado/br/gov/prodemge/seplag/fcrhprova/visao/index.zul");
	}

	//Metodo necessario para adaptar o fechamento do menu ao novo layout
	public void abreFechaMenu(Div menuBar) {
		String metodoJavascriptAbreFechaMenu = "abreFechaMenu('." + menuBar.getSclass().toString() + "')";
		Clients.evalJavaScript(metodoJavascriptAbreFechaMenu);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		configuraUsuarioUnidadeLogada();
	}
}
