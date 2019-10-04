package br.com.unifacisa.si2.damas.entity;

import br.com.unifacisa.si2.damas.Cor;

public class Tabuleiro {

	private static final int TAMANHO = 8;
	
	Peca[][] aux = new Peca[TAMANHO][TAMANHO];
	
	public Tabuleiro(Jogador jogador1, Jogador jogador2) {
		for (int i = 0; i <	 aux.length; i++) {
			for (int j = 0; j < aux.length; j++) {
				
				if(i != 3 && i!=4 && i<= 4) {
					if(i%2 != 0) {
						if(j % 2 == 0) {
							aux[i][j] = jogador1.getPeca(); 
						}
					}
					else if(i%2 == 0){
						if(j % 2 != 0) {
							aux[i][j] = jogador1.getPeca();
						}
					}
					else {
						aux[i][j] = null;
					}
				}
				
				else if(i != 3 && i != 4 && i > 4){
					if(i%2 != 0) {
						if(j % 2 == 0) {
							aux[i][j] = jogador2.getPeca(); 
						}
					}
					else if(i%2 == 0){
						if(j % 2 != 0) {
							aux[i][j] = jogador2.getPeca();
						}
					}
				}
				else {
					aux[i][j] =null;
				}
			}
		}
	}
	
	public Peca[][] array(){
		return aux;
	}

	public static int getTamanho() {
		return TAMANHO;
	}

	public static void main(String[] args) {
		Jogador jogador1 = new Jogador("Matheus", new Peca(false, Cor.BLACK));
		
		Jogador jogador2 = new Jogador("James", new Peca(false, Cor.WHITE));
		
		Tabuleiro tabuleiro = new Tabuleiro(jogador1, jogador2);
		
		for (int i = 0; i < tabuleiro.array().length; i++) {
			for (int j = 0; j < tabuleiro.array().length; j++) {
				if(tabuleiro.array()[i][j] == null) {
					System.out.print("  ");
				} 
				else {
					System.out.print(tabuleiro.array()[i][j].getColor());
			}
			}
			System.out.println();
		}
		
	}

}
