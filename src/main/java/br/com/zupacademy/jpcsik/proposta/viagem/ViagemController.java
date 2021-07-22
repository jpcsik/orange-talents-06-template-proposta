package br.com.zupacademy.jpcsik.proposta.viagem;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.jpcsik.proposta.cartao.VerificarNumeroCartao;

@RestController
public class ViagemController {

	@Autowired
	private ViagemRepository viagemRepository;
	
	@Autowired
	private VerificarNumeroCartao verificarNumeroCartao;
	
	@PostMapping("/viagem/cadastrar/{numeroCartao}")
	public ResponseEntity<?> cadastrar(@PathVariable String numeroCartao, 
			@RequestHeader(value="User-Agent") String userAgent, HttpServletRequest request, 
			@RequestBody @Valid NovaViagemRequest novaViagem){
		
		//Verifica os dados que devem vir na requisição
		if(numeroCartao.isBlank() || userAgent.isBlank()) {
			return ResponseEntity.badRequest().build();
		}
		
		//Verifica se cartão existe
		verificarNumeroCartao.verificar(numeroCartao);
		
		Viagem viagem = novaViagem.toViagem(numeroCartao, numeroCartao, userAgent);
		//Salvar viagem
		viagemRepository.save(viagem);
		return ResponseEntity.ok().build();
		
	}
	
}
