package br.com.caelum.leilao.servico;

public class ano {

	public static boolean ISANOBISSEXTO(int ano) {
	    if (((ano % 4) == 0) && ((ano % 100) != 0)) return true; 
	        else if ((ano % 400) == 0) return true; 
	        else return false;                
	}
}
