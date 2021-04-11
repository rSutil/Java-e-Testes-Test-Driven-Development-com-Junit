package br.com.caelum.leilao.teste;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();

		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("maria");
	}

	@After
	public void finaliza() {
//		@After são executados após a execução do método de teste.
//		Utilizamos métodos @After quando nossos testes consomem recursos que precisam ser finalizados. 
//		Exemplos podem ser testes que acessam banco de dados, abrem arquivos, abrem sockets, e etc.
		System.out.println("fim");
	}

	@BeforeClass
	public static void testandoBeforeClass() {
//		Métodos anotados com @BeforeClass são executados apenas uma vez, antes de todos os métodos de teste.
		System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
//		O método anotado com @AfterClass, por sua vez, é executado uma vez, após a execução do último método de teste da classe.
		System.out.println("after class");
	}

	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemNenhumLanceDado() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 5 - Novo").constroi();
		leiloeiro.avalia(leilao);

	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		Leilao leilao = new CriadorDeLeilao().para("PlayStation 5 - Novo").lance(joao, 300.0).lance(maria, 400)
				.lance(jose, 350).constroi();

		leiloeiro.avalia(leilao);

		double maiorEsperado = 400.0;
		double menorEsperado = 300.0;
		double mediaEsperada = 350.0;

		assertThat(leiloeiro.getMaiorLance(), equalTo(maiorEsperado));
		assertThat(leiloeiro.getMediaLances(), equalTo(mediaEsperada));		
		assertThat(leiloeiro.getMenorLance(), equalTo(menorEsperado));
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {

		Leilao leilao = new CriadorDeLeilao().para("PlayStation 5 - Novo").lance(joao, 100).constroi();

		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getMaiorLance(), equalTo(100.0));
		assertThat(leiloeiro.getMediaLances(), equalTo(100.0));		
		assertThat(leiloeiro.getMenorLance(), equalTo(100.0));

	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {

		Leilao leilao = new CriadorDeLeilao().para("PlayStation 5 - Novo").lance(joao, 100.0).lance(maria, 200.0)
				.lance(joao, 300.0).lance(maria, 400.0).constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		
		assertThat(maiores, hasItems(
					new Lance(maria, 400.0),
					new Lance(joao, 300.0),
					new Lance(maria, 200.0)
		));
		
//		Foi substituito pelo assertThat a cima (Deixa legível o teste)	
		// Foi necessário criar o equals e hashCode, pois o assertThat usa o equals (Boa prática, sempre que criar equals gerar o hashCode)
//		assertEquals(400, maiores.get(0).getValor(), 0.00001);
//		assertEquals(300, maiores.get(1).getValor(), 0.00001);
//		assertEquals(200, maiores.get(2).getValor(), 0.00001);

	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemRandomica() {

		Leilao leilao = new CriadorDeLeilao().para("PlayStation 5 - Novo").lance(joao, 200.0).lance(maria, 450.0)
				.lance(joao, 120.0).lance(maria, 700).lance(joao, 630.0).lance(maria, 230).constroi();

		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getMaiorLance(), equalTo(170.0));	
		assertThat(leiloeiro.getMenorLance(), equalTo(120.0));
	}
}
