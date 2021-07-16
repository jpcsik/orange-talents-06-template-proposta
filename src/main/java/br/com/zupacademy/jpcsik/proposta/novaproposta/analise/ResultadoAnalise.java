package br.com.zupacademy.jpcsik.proposta.novaproposta.analise;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class ResultadoAnalise {

	//Unico atributo que preciso receber do serviço de analise
	private StatusResultadoSolicitacao resultadoSolicitacao;

	public ResultadoAnalise() {
	}

	@JsonCreator(mode = Mode.PROPERTIES)
	public ResultadoAnalise(StatusResultadoSolicitacao resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public StatusResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
	
}
