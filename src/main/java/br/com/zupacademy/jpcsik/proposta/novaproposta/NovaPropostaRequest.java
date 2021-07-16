package br.com.zupacademy.jpcsik.proposta.novaproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.jpcsik.proposta.validacao.CpfCnpj;

public class NovaPropostaRequest {

	@NotNull
	@CpfCnpj
	private final String documento;
	@NotBlank
	@Email
	private final String email;
	@NotBlank
	private final String nome;
	@NotBlank
	private final String endereco;
	@NotNull
	@Positive
	private final BigDecimal salario;
	
	@JsonCreator
	public NovaPropostaRequest(@NotNull String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Proposta toProposta(){
		return new Proposta(
				this.documento,
				this.email,
				this.nome,
				this.endereco,
				this.salario);
	}

	public String getDocumento() {
		return documento;
	}
	
}
