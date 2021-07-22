package br.com.zupacademy.jpcsik.proposta.carteira;

import javax.validation.Valid;

public class SolicitacaoInclusaoCarteira {

	private String email;
	private String carteira;

	public SolicitacaoInclusaoCarteira(@Valid NovaCarteiraRequest carteira) {
		this.email = carteira.getEmail();
		this.carteira = carteira.getCarteira().toString();
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}
	
	
}
