package br.com.zupacademy.jpcsik.proposta.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import feign.FeignException;
import feign.FeignException.FeignClientException;

@Service
public class VerificarNumeroCartao {

	@Autowired
	private ApiCartoes apiCartoes;
	
	//Verifica se o numero do cartão realmente pertence a algum cartão cadastrado no sistema
	public void verificar(String numeroCartao) {
		
		try {
			apiCartoes.existeCartao(numeroCartao);
		} catch (FeignClientException e) {
			if(e.status() == 404) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não cadastrado!");
			}
		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Serviço indisponivel!");
		}
	
	}
	
}
