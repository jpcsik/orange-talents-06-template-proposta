package br.com.zupacademy.jpcsik.proposta.novaproposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="${analise.solicitacao.url}", name="ServicoAnaliseSolicitacao")
public interface ServicoAnaliseSolicitacao {

	@PostMapping
	ResultadoAnalise analise(@RequestBody SolicitacaoAnalise proposta);
}
