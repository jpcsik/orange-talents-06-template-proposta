package br.com.zupacademy.jpcsik.proposta.proposta;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);

	//Busca todas as propostas pelo status e pelo numero do cartão
	Collection<Optional<Proposta>> findAllByStatusAndNumeroCartao(StatusProposta status, String numeroCartao);

	Optional<Proposta> findByNumeroCartao(String numeroCartao);
}
