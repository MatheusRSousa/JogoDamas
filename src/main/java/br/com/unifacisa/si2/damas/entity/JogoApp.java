package br.com.unifacisa.si2.damas.entity;

import java.util.Scanner;

import br.com.unifacisa.si2.damas.Cor;

public class JogoApp {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		Jogador jogador1 = new Jogador("Matheus", new Peca(false, Cor.BLACK));
		
		Jogador jogador2 = new Jogador("James", new Peca(false, Cor.WHITE));
		
		Tabuleiro tabuleiro = new Tabuleiro(jogador1, jogador2, 8);
		
		Jogo jogo = new Jogo(jogador1, jogador2, tabuleiro);
	
		Peca peca = null;
		jogo.imprimirTabuleiro();
		
		System.out.println();
		
		int cont = 0;
		while(cont < 2) {
			peca = null;
			while (peca == null) {
				int x = teclado.nextInt();
				int y = teclado.nextInt();
				
				peca = jogo.escolherPeca(x, y);
			}
			
			jogo.imprimirTabuleiro();
			
			System.out.println();
			
			int x_saida = teclado.nextInt();
			
			int y_saida = teclado.nextInt();
			
			jogo.moverPeca(peca, x_saida, y_saida);
			
			System.out.println();
			
			jogo.imprimirTabuleiro();
		
			
		}
		teclado.close();
	}
}
