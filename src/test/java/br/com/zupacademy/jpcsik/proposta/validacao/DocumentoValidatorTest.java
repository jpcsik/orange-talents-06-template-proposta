package br.com.zupacademy.jpcsik.proposta.validacao;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.jpcsik.proposta.novaproposta.NovaPropostaRequest;
import br.com.zupacademy.jpcsik.proposta.novaproposta.Proposta;
import br.com.zupacademy.jpcsik.proposta.novaproposta.PropostaRepository;

class DocumentoValidatorTest {

	private final String documento = "123456789";
	private final Object target = new NovaPropostaRequest(documento, "email@email.com", "nome", "endereco", new BigDecimal(2000));
	private final Errors errors = new BeanPropertyBindingResult(target, "teste");
	
	@Test
	@DisplayName("Não deve aceitar documento repetido")
	void test1()  {
		PropostaRepository propostaRepository = Mockito.mock(PropostaRepository.class);
		DocumentoValidator validator = new DocumentoValidator(propostaRepository);
		
		Optional<Proposta> proposta = 
				Optional.of(new Proposta(documento, "email@email.com", "nome", "endereco", new BigDecimal(2000)));

		Mockito.when(propostaRepository.findByDocumento(documento)).thenReturn(proposta);
		
		Assertions.assertThrows(ResponseStatusException.class, () -> validator.validate(target, errors));
		
	}
	
	@Test
	@DisplayName("Deve aceitar documento se não encontrar outro igual")
	void test2()  {
		PropostaRepository propostaRepository = Mockito.mock(PropostaRepository.class);
		DocumentoValidator validator = new DocumentoValidator(propostaRepository);
		
		Mockito.when(propostaRepository.findByDocumento(documento)).thenReturn(Optional.empty());
		
		Assertions.assertDoesNotThrow(() -> validator.validate(target, errors));
		
	}
	
}
