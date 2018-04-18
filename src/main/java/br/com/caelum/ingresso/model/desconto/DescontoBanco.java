package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;
public class DescontoBanco implements Desconto {

	@Override
	public BigDecimal aplicaDesconto(BigDecimal precoOriginal) {
	return precoOriginal.subtract(BigDecimal.TEN);
	}
}
