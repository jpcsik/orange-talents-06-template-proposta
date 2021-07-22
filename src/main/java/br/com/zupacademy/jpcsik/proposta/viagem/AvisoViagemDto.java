package br.com.zupacademy.jpcsik.proposta.viagem;

public class AvisoViagemDto {

	  private String destino;
	  private String validoAte;

	  public AvisoViagemDto(Viagem viagem) {
		this.destino = viagem.getDestino();
		this.validoAte = viagem.getDataRetorno().toString();
	}

	public String getDestino() {
		return destino;
	}

	public String getValidoAte() {
		return validoAte;
	}
	  
}
