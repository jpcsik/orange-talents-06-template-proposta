package br.com.zupacademy.jpcsik.proposta.proposta;

public class DetalhePropostaResponse {

	private final String nome;
	private final StatusProposta status;
	private final String numeroCartao;
	
	public DetalhePropostaResponse(Proposta proposta) {
		this.nome = proposta.getNome();
		this.status = proposta.getStatus();
		this.numeroCartao = proposta.getNumeroCartao();
	}

	public String getNome() {
		return nome;
	}

	public StatusProposta getStatus() {
		return status;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
	}

}
