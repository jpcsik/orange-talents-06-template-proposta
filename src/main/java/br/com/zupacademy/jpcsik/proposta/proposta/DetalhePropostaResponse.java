package br.com.zupacademy.jpcsik.proposta.proposta;

public class DetalhePropostaResponse {

	private final String nome;
	private final StatusProposta status;
	
	public DetalhePropostaResponse(Proposta proposta) {
		this.nome = proposta.getNome();
		this.status = proposta.getStatus();
	}

	public String getNome() {
		return nome;
	}

	public StatusProposta getStatus() {
		return status;
	}

}
