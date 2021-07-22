package br.com.zupacademy.jpcsik.proposta.feign;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErroHandler implements ErrorDecoder{

	//Esse método trata dos possiveis erros ao se conectar a um serviço externo usando o Feign
	@Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 400) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel realizar a requisição!");

        }if (response.status() == 404) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não foi encontrado!");

        }if (response.status() == 422) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O processo já foi realizado!");

        }
        if (response.status() >= 500 && response.status() <= 599) {
        	new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Servidor está indisponivel!");
       }

        return new Exception(response.reason());
	}

}
