package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;


public class DescontoTest {

	private Sessao sessao;

	@Test
	public void naoDeveConcederDescontoParaIngressoNormal () {
		Ingresso ingresso = new Ingresso (sessao, new SemDesconto());
		BigDecimal precoEsperado = new BigDecimal ("32.50");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());			
		}
	
	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressoDeClientesDeBancos () {
		Ingresso ingresso = new Ingresso (sessao, new DescontoBanco());
		BigDecimal precoEsperado = new BigDecimal ("22.50");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressoDeEstudante() {
		Ingresso ingresso = new Ingresso (sessao, new DescontoEstudante());
		BigDecimal precoEsperado = new BigDecimal ("16.250");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressoDeIdoso() {
		Ingresso ingresso = new Ingresso (sessao, new DescontoIdoso());
		BigDecimal precoEsperado = new BigDecimal ("22.750");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Before
	public void preparaPreco() {
		Sala sala = new Sala ("Eldorado - IMAX", new BigDecimal ("20.50"));
		Filme filme = new Filme ("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal ("12.00"));
		sessao = new Sessao (filme, sala, LocalTime.parse("10:00:00"));
	}
}
