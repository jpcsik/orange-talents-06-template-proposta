package br.com.zupacademy.jpcsik.proposta.bloqueio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long>{

	Optional<Bloqueio> findByNumeroCartao(String numeroCartao);

}
