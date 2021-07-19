package br.com.zupacademy.jpcsik.proposta.proposta.analise;

import br.com.zupacademy.jpcsik.proposta.proposta.StatusProposta;

public enum StatusResultadoSolicitacao {

	COM_RESTRICAO,SEM_RESTRICAO;
	
	//Devolve o status da proposta referente
	public StatusProposta normaliza() {
		if(this.equals(COM_RESTRICAO)) {
			return StatusProposta.NAO_ELEGIVEL;
		}
		return StatusProposta.ELEGIVEL;
	}
}
