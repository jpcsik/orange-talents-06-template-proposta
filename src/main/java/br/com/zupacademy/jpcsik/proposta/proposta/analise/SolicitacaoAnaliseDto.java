package br.com.zupacademy.jpcsik.proposta.proposta.analise;

import org.springframework.security.crypto.encrypt.TextEncryptor;

import br.com.zupacademy.jpcsik.proposta.proposta.Proposta;

public class SolicitacaoAnaliseDto {

	private final String documento;
	private final String nome;
	private final String idProposta;

	public SolicitacaoAnaliseDto(Proposta proposta, TextEncryptor encryptor) {
		this.documento = encryptor.decrypt(proposta.getDocumento()); //Descriptografa o documento
		this.nome = proposta.getNome();
		this.idProposta = String.valueOf(proposta.getId()); //Parse do id, de Long para String
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

}
