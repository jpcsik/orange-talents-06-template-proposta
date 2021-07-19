package br.com.zupacademy.jpcsik.proposta.validacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.jpcsik.proposta.proposta.NovaPropostaRequest;
import br.com.zupacademy.jpcsik.proposta.proposta.Proposta;
import br.com.zupacademy.jpcsik.proposta.proposta.PropostaRepository;

@Component
public class DocumentoValidator implements Validator{

	private PropostaRepository propostaRepository;
	
	@Autowired
	public DocumentoValidator(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaPropostaRequest.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		NovaPropostaRequest request = (NovaPropostaRequest) target;
		
		Optional<Proposta> possivelDocumento = propostaRepository.findByDocumento(request.getDocumento());

		//Procura no banco de dados se já existe o documento e joga uma exceção que é tradada no handler
		if(possivelDocumento.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já está cadastrado!");
		};
	}

}