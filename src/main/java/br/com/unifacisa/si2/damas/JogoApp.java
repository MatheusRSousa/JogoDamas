package br.com.unifacisa.si2.damas;


import java.util.List;

import java.util.Scanner;


import br.com.unifacisa.si2.damas.builder.BuilderDama;
import br.com.unifacisa.si2.damas.entity.JogadasPossiveis;
import br.com.unifacisa.si2.damas.entity.Jogo;
import br.com.unifacisa.si2.damas.entity.Peca;


public class JogoApp {

	
	public static void main(String[] args) throws Exception {
		Scanner teclado = new Scanner(System.in);
		
		String nomeJogador2;
		String nomeJogador1;
		int corPecaJogador1;
		int tipoTabuleiro;
		
		System.out.print("Digite o nome do primeiro Jogador: ");
		nomeJogador1 = teclado.next();
		System.out.print("Escolha a cor da peca: Digite 1 para branco, 2 para preto: ");
		corPecaJogador1 = teclado.nextInt();
		System.out.print("Digite o nome do segundo Jogador: ");
		nomeJogador2 = teclado.next();
		
		System.out.println("Digite O tipo de Tabuleiro que gostaria de jogar\n1-Tabuleiro pequeno (8x8)\n"
				+ "2-Tabuleiro medio (10x10)\n3-Tabuleiro medio (12x12)");
		tipoTabuleiro = teclado.nextInt();
		
		BuilderDama builder = new BuilderDama(tipoTabuleiro, nomeJogador1, nomeJogador2, corPecaJogador1);
		
		Jogo jogo = builder.getJogo();
		
		Peca peca = null;
		
		int linha = 0;
		String coluna= "";
		String coluna_saida = "";
		int linha_saida = 0;
		
		//iniciou o jogo
		while(!jogo.acabouJogo()) {
			peca = null;
			while (peca == null) {
				System.out.println();
				jogo.imprimirTabuleiro();	
				System.out.println();
				System.out.println("Vez do jogador "+ jogo.getDaVez().getNome() + "(" + jogo.getDaVez().getPeca().getCor() + ")");
				System.out.println("Escolha sua peça ");
				System.out.print("Coluna :");
				linha = teclado.nextInt();
				if(linha!= -1) {
					System.out.print("Linha :");
					coluna = teclado.next();
					System.out.println();
					peca = jogo.escolherPeca(linha, coluna);
					
				}else{ 
					jogo.voltarJogada();
				}
			}
			jogo.JogadaPossivel(linha, coluna);
			List<JogadasPossiveis> jogadasPossiveis = jogo.getJogadasPossiveis();
			List<JogadasPossiveis> possiveisComidas = jogo.getPossiveisComidas();
			List<JogadasPossiveis> possiveisAtaques = jogo.getPossiveisAtaques();
			if(jogadasPossiveis.size() > 0 || possiveisAtaques.size() > 0) {
				for (JogadasPossiveis jogadas : jogadasPossiveis) {
					int num = jogadas.getColuna()+97;
					String letra = String.valueOf((char)num);
					System.out.println();
					System.out.println("Jogadas possiveis: ");
					System.out.print("Coluna " + jogadas.getLinha());
					System.out.println(" Linha " + letra);
					System.out.println();
				}
				for (JogadasPossiveis jogadas : possiveisAtaques) {
					int num = jogadas.getColuna()+97;
					String letra = String.valueOf((char)num);
					System.out.println();
					System.out.println("Possiveis ataques: ");
					System.out.print("Coluna " + jogadas.getLinha());
					System.out.println(" Linha " + letra);
					System.out.println();
				}
				for (JogadasPossiveis jogadas : possiveisComidas) {
					int num = jogadas.getColuna()+97;
					String letra = String.valueOf((char)num);
					System.out.println();
					System.out.println("Possiveis comidas: ");
					System.out.print("Coluna " + jogadas.getLinha());
					System.out.println(" Linha " + letra);
					System.out.println();
				}
			}
			
			System.out.println();
			System.out.print("Coluna onde quer jogar: ");
			linha_saida = teclado.nextInt();
			System.out.print("Linha onde quer jogar: ");
			coluna_saida = teclado.next();
			
			
			jogo.moverPeca(peca, linha_saida, coluna_saida);
		
			}

		teclado.close();
		System.out.println("Vencedor do jogo é : " + jogo.getVencedor());
	}
	
}
