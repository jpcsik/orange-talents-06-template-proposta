package br.com.zupacademy.jpcsik.proposta.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.jpcsik.proposta.proposta.NovaPropostaRequest;
import br.com.zupacademy.jpcsik.proposta.proposta.Proposta;
import br.com.zupacademy.jpcsik.proposta.proposta.PropostaRepository;

@Component
public class DocumentoValidator implements Validator{

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Value("${encryptor.password}")
	private String password;
	
	@Value("${encryptor.salt}")
	private String salt;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaPropostaRequest.class.isAssignableFrom(clazz);
	}
	
	//Valida se o documento que vêm na requisição já existe
	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		NovaPropostaRequest request = (NovaPropostaRequest) target;
		
		TextEncryptor encryptor = Encryptors.text(password, salt);

		List<Proposta> propostas = propostaRepository.findAll();

		//Descriptografa os documentos para fazer a verificação 
		propostas.forEach(proposta -> {
			String documento = encryptor.decrypt(proposta.getDocumento());
			if(documento.equals(request.getDocumento())) {
				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já está cadastrado!");
			}
		});
		
	}

}