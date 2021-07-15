package br.com.zupacademy.jpcsik.proposta.errohandler;

import java.util.Collection;

public class ErroDto {

	private Collection<String> mensagens;
	
	public ErroDto(Collection<String> mensagens) {
		this.mensagens = mensagens;
	}

	public Collection<String> getMensagens() {
		return mensagens;
	}
	
}
