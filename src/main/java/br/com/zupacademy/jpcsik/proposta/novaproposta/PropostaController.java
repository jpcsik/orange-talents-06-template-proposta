package br.com.zupacademy.jpcsik.proposta.novaproposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.jpcsik.proposta.validacao.DocumentoValidator;

@RestController
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private DocumentoValidator documentoValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(documentoValidator);
	}
	
	@PostMapping("/proposta/cadastrar")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPropostaRequest novaProposta, UriComponentsBuilder uriBuilder){
		
		Proposta proposta = novaProposta.toProposta();
		propostaRepository.save(proposta);
		
		URI uri = uriBuilder.buildAndExpand("/proposta/{id}", proposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
