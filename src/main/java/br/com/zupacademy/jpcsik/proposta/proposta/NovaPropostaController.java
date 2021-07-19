package br.com.zupacademy.jpcsik.proposta.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.jpcsik.proposta.proposta.analise.ApiAnaliseSolicitacao;
import br.com.zupacademy.jpcsik.proposta.proposta.analise.ResultadoAnaliseDto;
import br.com.zupacademy.jpcsik.proposta.proposta.analise.SolicitacaoAnaliseDto;
import br.com.zupacademy.jpcsik.proposta.validacao.DocumentoValidator;
import feign.FeignException;
import feign.FeignException.FeignClientException;

@RestController
public class NovaPropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private ApiAnaliseSolicitacao apiAnaliseSolicitacao;
	
	//Valida se o documento que vêm na requisição já existe
	@Autowired
	private DocumentoValidator documentoValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(documentoValidator);
	}
	
	@PostMapping("/proposta/cadastrar")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPropostaRequest novaProposta, UriComponentsBuilder uriBuilder) {
		Proposta proposta = novaProposta.toProposta();
		
		//Salva a proposta no banco gerando um Id
		propostaRepository.save(proposta);
		
		//Trata as possíveis exceções que o serviço de analise pode jogar
		try {
			ResultadoAnaliseDto propostaAnalisada = apiAnaliseSolicitacao.analise(new SolicitacaoAnaliseDto(proposta));
			proposta.definirStatus(propostaAnalisada.getResultadoSolicitacao().normaliza());
		} catch (FeignClientException e) {
			proposta.definirStatus(StatusProposta.NAO_ELEGIVEL);
		} catch(FeignException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Serviço indisponivel!");
		}
		
		//Atualiza a proposta com o novo status
		propostaRepository.save(proposta);
		
		//Cria a url da nova proposta
		URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}

}
