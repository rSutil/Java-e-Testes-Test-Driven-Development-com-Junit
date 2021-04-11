package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.servico.ano;

public class TesteAno {
	
	@Test
	public void testeAnoDe2024() {
		assertEquals(true, ano.ISANOBISSEXTO(2024));
	}
	
	@Test
	public void testeAnoDe2300DivisivelPor100() {
		assertEquals(false, ano.ISANOBISSEXTO(2300));
	}
	
	@Test
	public void testeAnoDe2000DivisivelPor400() {
		assertEquals(true, ano.ISANOBISSEXTO(2000));
	}
}
