package br.com.zupacademy.jpcsik.proposta.carteira;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CarteiraDigital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Email
	private String email;
	@NotNull
	@Enumerated
	private TipoCarteira carteira;
	@NotBlank
	private String numeroCartao;

	@Deprecated
	public CarteiraDigital() {
	}

	public CarteiraDigital(@NotBlank @Email String email, @NotNull TipoCarteira carteira,
			@NotBlank String numeroCartao) {
		this.email = email;
		this.carteira = carteira;
		this.numeroCartao = numeroCartao;
	}

	public Long getId() {
		return id;
	}

}
