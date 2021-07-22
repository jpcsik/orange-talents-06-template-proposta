package br.com.zupacademy.jpcsik.proposta.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
public class Viagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String numeroCartao;
	@NotBlank
	private String destino;
	@NotNull
	@Future
	private LocalDate dataRetorno;
	@NotNull
	@PastOrPresent
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@NotBlank
	private String ip;
	@NotBlank
	private String userAgent;
	
	@Deprecated
	public Viagem() {
	}

	public Viagem(@NotBlank String numeroCartao, @NotBlank String destino, @NotNull @Future LocalDate dataRetorno,
			@NotBlank String ip, @NotBlank String userAgent) {
		this.numeroCartao = numeroCartao;
		this.destino = destino;
		this.dataRetorno = dataRetorno;
		this.ip = ip;
		this.userAgent = userAgent;
	}
	
}
