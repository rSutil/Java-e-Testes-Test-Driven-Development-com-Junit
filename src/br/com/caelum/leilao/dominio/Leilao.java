package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		int total = 0;
		
		total = qtdDeLancesDo(lance.getUsuario());		
		
		if (lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}
	
	public void dobraLance(Usuario usuario) {
		double ultimoLance = ultimoLanceDo(usuario);
		
		if (ultimoLance > 0) {
			this.propoe(new Lance(usuario, ultimoLance * 2));
		}		
	}

	private boolean podeDarLance(Usuario usuario) {
		return !UltimoLanceDado().getUsuario().equals(usuario) && qtdDeLancesDo(usuario) < 5;
	}

	private int qtdDeLancesDo(Usuario usuario) {
		int total = 0;
		for (Lance l : lances) {
			if(l.getUsuario().equals(usuario)) total++;
		}
		return total;
	}

	private Lance UltimoLanceDado() {
		return lances.get(lances.size() - 1);
	}
	
	private double ultimoLanceDo(Usuario usuario) {
		double ultimo = 0;
		for (Lance lance : lances) {
			if (lance.getUsuario().equals(usuario)) {
				ultimo = lance.getValor();
			}
		}

		return ultimo;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	
	
}
