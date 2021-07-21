package br.com.zupacademy.jpcsik.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="${api.cartoes.url}", name="ApiCartoes")
public interface ApiCartoes {
	
	//Pega informações do novo cartão
	@GetMapping("/api/cartoes")
	NumeroCartaoDto novoCartao(@PathVariable("IdProposta") Long idProposta);
	
}
