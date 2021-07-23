package br.com.zupacademy.jpcsik.proposta.carteira;

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
public class CarteiraController {

	@Autowired
	private CarteiraRepository carteiraRepository;
	
	@Autowired
	private ApiCartoes apiCartoes;
	
	@PostMapping("carteira/cadastrar/{numeroCartao}")
	@Transactional
	public ResponseEntity<?> cadastrar(@PathVariable String numeroCartao, @RequestBody @Valid NovaCarteiraRequest carteira, UriComponentsBuilder uriBuilder){
		
		//Verifica se numero do cartão é valido
		apiCartoes.existeCartao(numeroCartao);
		
		//Faz associação do carteira digital com o cartão
		//Caso a carteira já esteja associada com um cartão o serviço joga uma exception que é tratada no handler
		apiCartoes.associarCarteira(numeroCartao, new SolicitacaoInclusaoCarteira(carteira));

		//Salva nova carteira digital no banco
		CarteiraDigital carteiraDigital = carteira.toCarteira(numeroCartao);
		carteiraRepository.save(carteiraDigital);
		
		//Cria url de resposta
		URI uri = uriBuilder.path("/carteira/{id}").buildAndExpand(carteiraDigital.getId(), "id").toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
}
