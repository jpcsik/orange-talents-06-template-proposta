package br.com.zupacademy.jpcsik.proposta.validacao;

public class ErroValidacaoDto {

	private String field;
	private String mensagem;
	
	public ErroValidacaoDto(String field, String mensagem) {
		this.field = field;
		this.mensagem = mensagem;
		
	}

	public String getField() {
		return field;
	}

	public String getMensagem() {
		return mensagem;
	}
	
}
