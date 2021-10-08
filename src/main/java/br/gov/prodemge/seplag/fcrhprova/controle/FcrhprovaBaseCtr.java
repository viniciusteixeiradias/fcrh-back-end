package br.gov.prodemge.seplag.fcrhprova.controle;

import br.gov.prodemge.seplag.fcrhprova.entidades.FcrhprovaBaseVO;
import br.gov.prodigio.controle.ProCtr;
import br.gov.prodigio.visao.helper.ProMessageHelper;

public class FcrhprovaBaseCtr<ENTITY extends FcrhprovaBaseVO> extends ProCtr<ENTITY> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7461391100856773714L;
	@SuppressWarnings("unchecked")
	private ProMessageHelper messageHelper = new FcrhprovaBaseMessageHelper((FcrhprovaBaseCtr<FcrhprovaBaseVO>) this);

	@Override 
	public ProMessageHelper getMessagesHelper() {
		return messageHelper;
	} 

}
 
