package br.com.zupacademy.jpcsik.proposta.biometria;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.jpcsik.proposta.cartao.ApiCartoes;

@RestController
public class BiometriaController {

	@Autowired
	private BiometriaRepository biometriaRepository;
	
	@Autowired
	private ApiCartoes apiCartoes;
	
	@PostMapping("/biometria/cadastrar/{numeroCartao}")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaBiometriaRequest novaBiometria,
			@PathVariable String numeroCartao, UriComponentsBuilder uriBuilder) {
		
		//Verifica se cartão existe
		apiCartoes.existeCartao(numeroCartao);

		//Salva a biometria
		Biometria biometria = novaBiometria.toBiometria(numeroCartao);
		biometriaRepository.save(biometria);

		//Cria uri de resposta
		URI uri = uriBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

}
