package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public interface Desconto {
	public BigDecimal aplicaDesconto(BigDecimal precoOriginal);
}
