package br.com.zupacademy.jpcsik.proposta.cartao;

import br.com.zupacademy.jpcsik.proposta.novaproposta.Proposta;

public class NovoCartaoDto {

	private final String documento;
	private final String nome;
	private final Long idProposta;

	public NovoCartaoDto(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}

}
