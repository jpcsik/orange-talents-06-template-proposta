package br.com.zupacademy.jpcsik.proposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
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

	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotNull String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

}
