package br.com.zupacademy.jpcsik.proposta.novaproposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@PostMapping("/proposta/cadastrar")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPropostaRequest novaProposta, UriComponentsBuilder uriBuilder){
		
		Proposta proposta = novaProposta.toProposta();
		propostaRepository.save(proposta);
		
		URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
