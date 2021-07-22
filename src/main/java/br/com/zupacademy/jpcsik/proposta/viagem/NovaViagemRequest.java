package br.com.zupacademy.jpcsik.proposta.viagem;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class NovaViagemRequest {

	@NotBlank
	private String destino;
	@NotNull
	@Future
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataRetorno;

	@JsonCreator
	public NovaViagemRequest(String destino, LocalDate dataRetorno) {
		this.destino = destino;
		this.dataRetorno = dataRetorno;
	}
	
	public Viagem toViagem(String numeroCartao, String ip, String userAgent) {
		return new Viagem(
				numeroCartao,
				destino,
				dataRetorno,
				ip,
				userAgent);
	}
	
}
