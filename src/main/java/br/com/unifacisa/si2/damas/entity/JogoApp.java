package br.com.unifacisa.si2.damas.entity;

import java.util.List;
import java.util.Scanner;

import br.com.unifacisa.si2.damas.Cor;
import br.com.unifacisa.si2.damas.strategy.TabuleiroGrande;
import br.com.unifacisa.si2.damas.strategy.TabuleiroMedio;
import br.com.unifacisa.si2.damas.strategy.TabuleiroPequeno;

public class JogoApp {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		Jogador jogador1 = new Jogador("Matheus", new Peca(false, Cor.BLACK));
		
		Jogador jogador2 = new Jogador("James", new Peca(false, Cor.WHITE));
		Tabuleiro tabuleiro = null;
		int tipoTabuleiro = -1;
		while(tipoTabuleiro < 0 || tipoTabuleiro > 3) {
			System.out.println("Digite O tipo de Tabuleiro que gostaria de jogar\n1-Tabuleiro pequeno (8x8)\n"
					+ "2-Tabuleiro medio (10x10)\n3-Tabuleiro medio (12x12)");
			tipoTabuleiro = teclado.nextInt();
			switch (tipoTabuleiro) {
			case 1:
				tabuleiro = new Tabuleiro(new TabuleiroPequeno(),jogador1, jogador2);
				break;
			case 2:
				tabuleiro = new Tabuleiro(new TabuleiroMedio(),jogador1, jogador2);
				break;
			case 3:
				tabuleiro = new Tabuleiro(new TabuleiroGrande(),jogador1, jogador2);
				break;
			default:
				
				System.out.println("Digite um numero Valido");
				break;
			}
		}
		
		Jogo jogo = new Jogo(jogador1, jogador2, tabuleiro);
	
		Peca peca = null;
		jogo.imprimirTabuleiro();
		
		System.out.println();
		int linha = 0;
		int coluna= 0;
		int cont = 0;
		int coluna_saida = 0;
		int linha_saida = 0;
		while(cont < 2) {
			peca = null;
			while (peca == null) {
				System.out.println();
				System.out.println("Vez do jogador "+ jogo.getDaVez().getNome() + "(" + jogo.getDaVez().getPeca().getCor() + ")");
				System.out.println("Escolha sua peça ");
				System.out.print("Linha :");
				linha = teclado.nextInt();
				System.out.print("Coluna :");
				coluna = teclado.nextInt();
				System.out.println();
				peca = jogo.escolherPeca(linha, coluna);
				
			}
			jogo.JogadaPossivel(linha, coluna);
			List<JogadasPossiveis> jogadasPossiveis = jogo.getJogadasPossiveis();
			List<JogadasPossiveis> possiveisComidas = jogo.getPossiveisComidas();
			List<JogadasPossiveis> possiveisAtaques = jogo.getPossiveisAtaques();
			if(jogadasPossiveis.size() > 0) {
				for (JogadasPossiveis jogadas : jogadasPossiveis) {
					System.out.println();
					System.out.println("Jogadas possiveis: ");
					System.out.print("Linha " + jogadas.getLinha());
					System.out.println(" Coluna " + jogadas.getColuna());
					System.out.println();
				}
				for (JogadasPossiveis jogadas : possiveisAtaques) {
					System.out.println();
					System.out.println("Possiveis ataques: ");
					System.out.print("Linha " + jogadas.getLinha());
					System.out.println(" Coluna " + jogadas.getColuna());
					System.out.println();
				}
				for (JogadasPossiveis jogadas : possiveisComidas) {
					System.out.println();
					System.out.println("Possiveis comidas: ");
					System.out.print("Linha " + jogadas.getLinha());
					System.out.println(" Coluna " + jogadas.getColuna());
					System.out.println();
				}
			}
			
			System.out.println();
			System.out.print("Linha onde quer jogar: ");
			linha_saida = teclado.nextInt();
			System.out.print("Coluna onde quer jogar: ");
			coluna_saida = teclado.nextInt();
			
			
			jogo.moverPeca(peca, linha_saida, coluna_saida);
			
			
			System.out.println();
			
			jogo.imprimirTabuleiro();
		
			
		}
		teclado.close();
	}
}
