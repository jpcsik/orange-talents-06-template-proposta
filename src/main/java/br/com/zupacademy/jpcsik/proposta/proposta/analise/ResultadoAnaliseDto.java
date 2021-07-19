package br.com.zupacademy.jpcsik.proposta.proposta.analise;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class ResultadoAnaliseDto {

	//Unico atributo que preciso receber do serviço de analise
	private final StatusResultadoSolicitacao resultadoSolicitacao;

	@JsonCreator(mode = Mode.PROPERTIES)
	public ResultadoAnaliseDto(StatusResultadoSolicitacao resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public StatusResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
	
}
