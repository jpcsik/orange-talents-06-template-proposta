package br.com.zupacademy.jpcsik.proposta.carteira;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

public class NovaCarteiraRequest {

	@NotBlank
	@Email
	private String email;
	@NotNull
	@Enumerated
	private TipoCarteira carteira;

	@JsonCreator
	public NovaCarteiraRequest(@NotBlank @Email String email, @NotNull TipoCarteira carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public CarteiraDigital toCarteira(String numeroCartao) {
		return new CarteiraDigital(
				email,
				carteira,
				numeroCartao);
	}

	public String getEmail() {
		return email;
	}
	
	public TipoCarteira getCarteira() {
		return carteira;
	}
}
