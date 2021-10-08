package br.gov.prodemge.seplag.fcrhprova.controle;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import br.gov.prodemge.seplag.fcrhprova.entidades.FcrhprovaBaseVO;
import br.gov.prodigio.visao.helper.ProMessageHelper;

public class FcrhprovaBaseMessageHelper extends ProMessageHelper {
	@Override
	public void emiteMensagemSucesso(String msg, Component component) {
		Clients.showNotification(msg, "info", component, "middle_center", 1000, true);
	}

	public FcrhprovaBaseMessageHelper(FcrhprovaBaseCtr<FcrhprovaBaseVO> ctr) {
		super(ctr);
	}
}
