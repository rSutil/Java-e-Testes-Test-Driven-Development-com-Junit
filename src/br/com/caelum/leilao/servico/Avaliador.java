package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	
	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	private double mediaLances = 0;
	private List<Lance> maiores;	

	public void avalia(Leilao leilao) {
		if(leilao.getLances().size() == 0) {
			throw new RuntimeException("Não é possível avaliar um leilão sem lances!");
		}
		
		double total = 0;
		
		for (Lance lance : leilao.getLances()) {
			
			total += lance.getValor();
			if (lance.getValor() > maiorLance) {
				this.maiorLance = lance.getValor();
			} 
			
			if (lance.getValor() < menorLance) {
				this.menorLance = lance.getValor();
			}			
		}
		
		this.mediaLances = total / leilao.getLances().size();
		
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>() {
			public int compare(Lance l1, Lance l2) {
				if (l1.getValor() < l2.getValor()) return 1;
				if (l1.getValor() > l2.getValor()) return -1;
				return 0;
			}
		});
		
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}
	
	public List<Lance> getTresMaiores() {
		return maiores;
	}
	
	public double getMaiorLance() {
		return maiorLance;
	}
	
	public double getMenorLance() {
		return menorLance;
	}
	
	public double getMediaLances() {
		return mediaLances;
	}
}
