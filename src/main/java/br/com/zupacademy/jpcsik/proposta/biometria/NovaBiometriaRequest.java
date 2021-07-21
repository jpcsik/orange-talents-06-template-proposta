package br.com.zupacademy.jpcsik.proposta.biometria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class NovaBiometriaRequest {

	@NotBlank
	//Valida se string está em Base64
	@Pattern(regexp = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$", message = "Formato invalido!")
	private final String biometria;

	@JsonCreator(mode = Mode.PROPERTIES)
	public NovaBiometriaRequest(String biometria) {
		this.biometria = biometria;
	}
	
	public String getBiometria() {
		return biometria;
	}
	
	public Biometria toBiometria(String numeroCartao) {
		return new Biometria(
				biometria,
				numeroCartao);
	}
	
}
