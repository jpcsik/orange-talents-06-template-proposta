package br.com.zupacademy.jpcsik.proposta.cartao;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zupacademy.jpcsik.proposta.viagem.AvisoViagemDto;

@FeignClient(url="${api.cartoes.url}", name="ApiCartoes")
public interface ApiCartoes {
	
	//Pega informações do novo cartão
	@GetMapping("/api/cartoes")
	NumeroCartaoDto novoCartao(@PathVariable("IdProposta") Long idProposta);
	
	//Verifica se cartão existe
	@GetMapping("/api/cartoes/{id}")
	void existeCartao(@PathVariable("id") String numeroCartao);
	
	//Faz requisição para bloquear cartão
	@PostMapping("/api/cartoes/{id}/bloqueios")
	void solicitarBloqueio(@PathVariable("id") String numeroCartao, Map<String, Object> map);
	
	//Notifica sistema bancario sobre nova viagem
	@PostMapping("/api/cartoes/{id}/avisos")
	void avisoViagem(@PathVariable("id") String numeroCartao, AvisoViagemDto aviso);

}
