package br.com.unifacisa.si2.damas.strategy;

import br.com.unifacisa.si2.damas.entity.Jogador;
import br.com.unifacisa.si2.damas.entity.Peca;

public class TabuleiroMedio implements CriarTabuleiro {
private static final int LADO_NORTE = 0;
	
	private static final int LADO_SUL = 1;
	
	Peca[][] tabuleiro;

	private static int TAMANHO = 10;
	
	public Peca[][] criarTabuleiro(Jogador jogador1, Jogador jogador2) {
		jogador1.getPeca().setLadoTabuleiro(LADO_NORTE);
		jogador2.getPeca().setLadoTabuleiro(LADO_SUL);
		tabuleiro = new Peca[TAMANHO][TAMANHO];
		
		for (int i = 0; i <	 tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				
				if(i < (TAMANHO/2) -1) {
					if(i%2 != 0) {
						if(j % 2 == 0) {
							tabuleiro[i][j] = new Peca(false, jogador1.getPeca().getCor()); 
						}
					}
					else if(i%2 == 0){
						if(j % 2 != 0) {
							tabuleiro[i][j] = new Peca(false, jogador1.getPeca().getCor()); 
						}
					}
					else {
						tabuleiro[i][j] = null;
					}
				}
				
				else if(i > TAMANHO/2){
					if(i%2 != 0) {
						if(j % 2 == 0) {
							tabuleiro[i][j] = new Peca(false, jogador2.getPeca().getCor());  
						}
					}
					else if(i%2 == 0){
						if(j % 2 != 0) {
							tabuleiro[i][j] = new Peca(false, jogador2.getPeca().getCor()); 
						}
					}
				}
				else {
					tabuleiro[i][j] =null;
				}
			}
		}
	
	return tabuleiro;
	}

}
