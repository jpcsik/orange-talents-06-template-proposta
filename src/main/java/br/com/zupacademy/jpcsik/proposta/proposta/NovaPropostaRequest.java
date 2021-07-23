package br.com.zupacademy.jpcsik.proposta.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.security.crypto.encrypt.TextEncryptor;

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
	
	public Proposta toProposta(TextEncryptor encryptor){
		return new Proposta(
				encryptor.encrypt(this.documento), //Criptografa o documento para ser salvo
				this.email,
				this.nome,
				this.endereco,
				this.salario);
	}

	public String getDocumento() {
		return documento;
	}
	
}
