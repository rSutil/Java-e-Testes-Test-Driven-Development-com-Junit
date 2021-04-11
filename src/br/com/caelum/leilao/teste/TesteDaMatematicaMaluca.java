package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.matematica.MatematicaMaluca;

public class TesteDaMatematicaMaluca {
	@Test
	public void numeroMaiorQue30() {
		
		MatematicaMaluca mat = new MatematicaMaluca();
		
		assertEquals(124, mat.contaMaluca(31), 0.0001);
	}
	
	@Test
	public void numeroMaiorQue10Menor30() {
		
		MatematicaMaluca mat = new MatematicaMaluca();
		
		assertEquals(33, mat.contaMaluca(11), 0.0001);
	}
	
	@Test
	public void numeroMenorQue10() {
		
		MatematicaMaluca mat = new MatematicaMaluca();
		
		assertEquals(8, mat.contaMaluca(4), 0.0001);
	}
}
