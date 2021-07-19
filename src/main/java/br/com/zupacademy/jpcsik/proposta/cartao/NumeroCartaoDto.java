package br.com.zupacademy.jpcsik.proposta.cartao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class NumeroCartaoDto {

	private final String id;

	@JsonCreator(mode = Mode.PROPERTIES)
	public NumeroCartaoDto(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
