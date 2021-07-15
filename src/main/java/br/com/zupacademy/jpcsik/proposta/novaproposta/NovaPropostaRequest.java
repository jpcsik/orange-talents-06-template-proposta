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
	private String documento;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@NotNull
	@Positive
	private BigDecimal salario;
	
	@JsonCreator
	public NovaPropostaRequest(String documento, @NotBlank @Email String email, @NotBlank String nome,
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
