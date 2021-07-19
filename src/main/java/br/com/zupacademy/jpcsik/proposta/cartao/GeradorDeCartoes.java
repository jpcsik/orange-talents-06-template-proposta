package br.com.zupacademy.jpcsik.proposta.cartao;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.jpcsik.proposta.novaproposta.Proposta;
import br.com.zupacademy.jpcsik.proposta.novaproposta.PropostaRepository;
import br.com.zupacademy.jpcsik.proposta.novaproposta.StatusProposta;
import feign.FeignException;

@Component
public class GeradorDeCartoes {

	@Autowired
	private ApiCartoes apiCartoes;

	@Autowired
	private PropostaRepository propostaRepository;

	//Gera um novo cartão a partir de uma proposta
	private void gerar(Proposta proposta) {
		CartaoDto cartaoDto = apiCartoes.novoCartaoDto(new NovoCartaoDto(proposta));
		proposta.adicionaCartao(cartaoDto.getId());
		propostaRepository.save(proposta);
	}
	
	//Busca propostas elegiveis para gerar novos cartões periodicamente 
	@Scheduled(fixedDelay = 10000)
	private void novosCartoes() {
		Collection<Optional<Proposta>> propostasElegiveis = propostaRepository
				.findAllByStatusAndNumeroCartao(StatusProposta.ELEGIVEL, "NAO_TEM");

		try {
			propostasElegiveis.stream().forEach(proposta -> gerar(proposta.get()));
		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Serviço indisponivel!");
		}
		
	}
}
