package br.com.zupacademy.jpcsik.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="${api.cartoes.url}", name="ApiCartoes")
public interface ApiCartoes {

	@PostMapping("/api/cartoes")
	CartaoDto novoCartaoDto(@RequestBody NovoCartaoDto novoCartaoDto);
}
