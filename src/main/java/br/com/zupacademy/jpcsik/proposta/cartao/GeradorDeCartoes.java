package br.com.zupacademy.jpcsik.proposta.cartao;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.jpcsik.proposta.proposta.Proposta;
import br.com.zupacademy.jpcsik.proposta.proposta.PropostaRepository;
import br.com.zupacademy.jpcsik.proposta.proposta.StatusProposta;

@Component
public class GeradorDeCartoes {

	@Autowired
	private ApiCartoes apiCartoes;

	@Autowired
	private PropostaRepository propostaRepository;

	//Gera um novo cartão a partir de uma proposta
	private void gerar(Proposta proposta) {
		NumeroCartaoDto numeroCartaoDto = apiCartoes.novoCartao(proposta.getId());
		proposta.adicionaCartao(numeroCartaoDto.getId());
		proposta.definirStatus(StatusProposta.APROVADA);
		propostaRepository.save(proposta);
	}
	
	//Busca propostas elegiveis para gerar novos cartões periodicamente 
	@Scheduled(fixedDelay = 10000)
	private void novosCartoes() {
		Collection<Optional<Proposta>> propostasElegiveis = propostaRepository
				.findAllByStatusAndNumeroCartao(StatusProposta.ELEGIVEL, "NAO_TEM");

		//Para cada proposta elegivel eu tento "gerar" um cartão
		propostasElegiveis.stream().forEach(proposta -> gerar(proposta.get()));
		
		
	}
}
