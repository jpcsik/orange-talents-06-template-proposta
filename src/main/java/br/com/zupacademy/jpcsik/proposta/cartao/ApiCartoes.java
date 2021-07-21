package br.com.zupacademy.jpcsik.proposta.cartao;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url="${api.cartoes.url}", name="ApiCartoes")
public interface ApiCartoes {
	
	//Pega informações do novo cartão
	@GetMapping("/api/cartoes")
	NumeroCartaoDto novoCartao(@PathVariable("IdProposta") Long idProposta);
	
	//Faz requisição para bloquear cartão
	@PostMapping("/api/cartoes/{id}/bloqueios")
	void solicitarBloqueio(@PathVariable("id") String numeroCartao, Map<String, Object> map);

}
