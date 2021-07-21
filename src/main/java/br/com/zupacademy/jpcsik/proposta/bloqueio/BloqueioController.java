package br.com.zupacademy.jpcsik.proposta.bloqueio;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.jpcsik.proposta.proposta.Proposta;
import br.com.zupacademy.jpcsik.proposta.proposta.PropostaRepository;

@RestController
public class BloqueioController {

	@Autowired
	private BloqueioRepository bloqueioRepository;
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@PostMapping("/bloqueio/{numeroCartao}")
	@Transactional
	public ResponseEntity<?> bloqueio(@PathVariable String numeroCartao, 
			@RequestHeader(value="User-Agent") String userAgent, HttpServletRequest request) {
		
		//Verifica os dados que devem vir na requisição
		if(numeroCartao.isBlank() || userAgent.isBlank()) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<Proposta> possivelCartao = propostaRepository.findByNumeroCartao(numeroCartao);
		
		//Verifica se cartão existe
		if(possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Bloqueio bloqueio = new Bloqueio(numeroCartao, userAgent, request.getRemoteAddr());
		Optional<Bloqueio> possivelBloqueio = bloqueioRepository.findByNumeroCartao(numeroCartao);
		
		//Verifica se cartão já está bloqueado
		if(possivelBloqueio.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		
		//Salva o bloqueio do cartão
		bloqueioRepository.save(bloqueio);
		return ResponseEntity.ok().build();
		
	}
	
}
