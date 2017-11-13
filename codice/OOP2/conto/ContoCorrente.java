package conto;

import java.util.Date;

public class ContoCorrente {
	static int contatore;
	int numConto;
	private double saldo = 0;
	Date dataApertura;
	
	public ContoCorrente(){
		System.out.println("hai costruito un conto vuoto");
		numConto=numeraConto();
	}
	public ContoCorrente(double saldoIniziale){
		System.out.println("hai costruito un conto con un versamento iniziale");
		numConto=numeraConto();
	}
	public ContoCorrente(String nome){
		System.out.println("hai costruito il conto di: " +  nome);
		numConto=numeraConto();
	}
	
	private int numeraConto(){
		return contatore++;
	}
	
	public void versamento(double ammontare){
		System.out.println("Versamento euro " + ammontare);
		saldo = saldo + ammontare;
		
	}
	
	public void prelievo(double ammontare){
		System.out.println("Prelievo euro " + ammontare);
		saldo = saldo - ammontare;
		
	}
	
	public String stampaInfo(){
		
		String s = null;
		
		//System.out.println("Il numero di conto è " + numConto);
		s = "Il numero di conto è \t" + numConto;
		
		//System.out.println("il saldo disponibile è " + saldo);
		s += "\n il saldo disponibile è \t" + saldo;
		
		s += "\n--------------------------------------------------\n";
		
		return s;
	}
	

}
