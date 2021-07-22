package br.com.zupacademy.jpcsik.proposta.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.jpcsik.proposta.cartao.ApiCartoes;
import feign.FeignException;
import feign.FeignException.FeignClientException;

@Service
public class NotificarSistemaViagem {

	@Autowired
	private ApiCartoes apiCartoes;
	
	public void notificar(String numeroCartao, Viagem viagem) {

        //Tratamento da requisição já que o sistema legado pode jogar exceptions
		try {
			apiCartoes.avisoViagem(numeroCartao, new AvisoViagemDto(viagem));
		} catch (FeignClientException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Serviço indisponivel!");
		}
	}
	
}
