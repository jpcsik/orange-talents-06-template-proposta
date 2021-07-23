package br.com.zupacademy.jpcsik.proposta.feign;

import org.springframework.context.annotation.Bean;

public class FeignConfig {
   
	@Bean
   public FeignErroHandler errorDecoder() {
	   return new FeignErroHandler();
   }

}

