package br.com.zupacademy.jpcsik.proposta.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String numeroCartao;
	@NotBlank
	private String userAgent;
	@NotBlank
	private String remoteAddr;
	@PastOrPresent
	@NotNull
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(@NotBlank String numeroCartao, @NotBlank String userAgent, @NotBlank String remoteAddr) {
		super();
		this.numeroCartao = numeroCartao;
		this.userAgent = userAgent;
		this.remoteAddr = remoteAddr;
	}
	
}
