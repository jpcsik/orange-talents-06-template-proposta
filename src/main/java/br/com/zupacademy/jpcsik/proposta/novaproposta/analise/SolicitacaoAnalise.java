package br.com.zupacademy.jpcsik.proposta.novaproposta.analise;

import br.com.zupacademy.jpcsik.proposta.novaproposta.Proposta;

public class SolicitacaoAnalise {

	private String documento;
	private String nome;
	private String idProposta;

	public SolicitacaoAnalise(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = String.valueOf(proposta.getId()); //Parse do id, de Long para String
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

}
