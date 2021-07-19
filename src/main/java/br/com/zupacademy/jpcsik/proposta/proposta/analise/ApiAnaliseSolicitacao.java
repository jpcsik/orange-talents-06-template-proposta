package br.com.zupacademy.jpcsik.proposta.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="${analise.solicitacao.url}", name="ApiAnaliseSolicitacao")
public interface ApiAnaliseSolicitacao {

	@PostMapping
	ResultadoAnaliseDto analise(@RequestBody SolicitacaoAnaliseDto proposta);
}
