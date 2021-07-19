package br.com.zupacademy.jpcsik.proposta.novaproposta.analise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.zupacademy.jpcsik.proposta.proposta.StatusProposta;
import br.com.zupacademy.jpcsik.proposta.proposta.analise.StatusResultadoSolicitacao;

class StatusResultadoSolicitacaoTest {

	@Test
	@DisplayName("Metodo normaliza() deve retornar o status NAO_ELEGIVEL")
	void test() {
		StatusResultadoSolicitacao comRestricao = StatusResultadoSolicitacao.COM_RESTRICAO;
		Assertions.assertEquals(StatusProposta.NAO_ELEGIVEL, comRestricao.normaliza());
	}

	@Test
	@DisplayName("Metodo normaliza() deve retornar o status ELEGIVEL")
	void test1() {
		StatusResultadoSolicitacao comRestricao = StatusResultadoSolicitacao.SEM_RESTRICAO;
		Assertions.assertEquals(StatusProposta.ELEGIVEL, comRestricao.normaliza());
	}
}
