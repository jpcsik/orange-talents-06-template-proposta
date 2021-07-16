package br.com.zupacademy.jpcsik.proposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zupacademy.jpcsik.proposta.novaproposta.analise.StatusResultadoSolicitacao;

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
	@Enumerated
	private StatusProposta status;

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

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	//Define o status da proposta com base no status recebido da analise
	public void definirStatus(StatusResultadoSolicitacao statusResultadoSolicitacao) {
		Assert.notNull(statusResultadoSolicitacao, "Resultado da solicitação não pode ser nulo");
		this.status = statusResultadoSolicitacao.normaliza();
	}
	
}
