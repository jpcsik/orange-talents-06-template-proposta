package br.com.zupacademy.jpcsik.proposta.bloqueio;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.jpcsik.proposta.cartao.ApiCartoes;
import feign.FeignException;
import feign.FeignException.FeignClientException;

@Service
public class BloquearCartaoNoSistema {

	@Autowired
	private ApiCartoes apiCartoes;
	
	public void bloquear(String numeroCartao) {
		
		//Corpo da requisição para o sistema legado
		Map<String, Object> body = new HashMap<>();
        body.put("sistemaResponsavel", new String("proposta"));

        //Tratamento da requisição já que o sistema legado pode jogar exceptions
		try {
			apiCartoes.solicitarBloqueio(numeroCartao, body);
		} catch (FeignClientException e) {
			//Sistema já verifica se o cartão está bloqueado ou não
			if(e.status() == 422) {
				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já está bloqueado!");
			}
		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Serviço indisponivel!");
		}
	}
	
}
