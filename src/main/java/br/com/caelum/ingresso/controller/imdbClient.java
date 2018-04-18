package br.com.caelum.ingresso.controller;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.infra.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;
import org.apache.log4j.Logger;

@Component
public class imdbClient {
	Logger logger = Logger.getLogger(imdbClient.class);
	
	public Optional<DetalhesDoFilme> request(Filme filme) {
    	RestTemplate cliente = new RestTemplate();
    	String url = "https://imdb-fj22.herokuapp.com/imdb?title="+filme.getNome().replace(" ", "+");
    	try {
    		DetalhesDoFilme ddf = cliente.getForObject(url, DetalhesDoFilme.class);
    		return Optional.of(ddf);
    	} catch (RestClientException e) {
    		logger.error(e.getMessage(), e);
    		return Optional.empty();
		}
	}
}
