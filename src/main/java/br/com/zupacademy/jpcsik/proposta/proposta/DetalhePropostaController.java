package br.com.zupacademy.jpcsik.proposta.proposta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalhePropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	//Busca detalhes de uma proposta pelo seu id
	@GetMapping("proposta/detalhar/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id){
		Optional<Proposta> proposta = propostaRepository.findById(id);
		
		//Verifica se proposta existe
		if(proposta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(new DetalhePropostaResponse(proposta.get()));
	
	}
	
}
