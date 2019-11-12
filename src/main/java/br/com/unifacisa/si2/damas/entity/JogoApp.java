package br.com.unifacisa.si2.damas.entity;


import java.util.List;

import java.util.Scanner;


import br.com.unifacisa.si2.damas.builder.BuilderDama;
import br.com.unifacisa.si2.damas.memento.VoltarJogada;


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
		jogo.imprimirTabuleiro();
	
		Peca peca = null;
		
		
		System.out.println();
		int linha = 0;
		int coluna= 0;
		int cont = 0;
		int coluna_saida = 0;
		int linha_saida = 0;
		boolean voltouJogada  = false;
		VoltarJogada voltarJogada = new VoltarJogada();
		while(cont < 2) {
			peca = null;
			while (peca == null) {
				
				System.out.println();
				System.out.println("Vez do jogador "+ jogo.getDaVez().getNome() + "(" + jogo.getDaVez().getPeca().getCor() + ")");
				System.out.println("Escolha sua peça ");
				System.out.print("Linha :");
				linha = teclado.nextInt();
				if(linha!= -1) {
				
				System.out.print("Coluna :");
				coluna = teclado.nextInt();
				System.out.println();
				peca = jogo.escolherPeca(linha, coluna);
				}if (jogo.getQntdJogadas() == 0) {
					System.out.println("Impossivel Voltar Jogada");
					
				} else {
					VoltarJogada jogada = voltarJogada.getJogadaAtuais();
					VoltarJogada volta = voltarJogada.getJogadaAnterior();		
					VoltarJogada comidas = voltarJogada.getPecasComidas();
					
					if (comidas != null) {
						jogo.getTabuleiro().getTabuleiro()[comidas.getLinha()][comidas.getColuna()] = jogo.getDaVez().getPeca();	
					}
					System.out.println(volta.getColuna() + " " +volta.getLinha());

					voltouJogada = true;
					jogo.diminuiJogada();
					jogo.getTabuleiro().getTabuleiro()[volta.getLinha()][volta.getColuna()] = jogo.getNaoDaVez().getPeca();
					jogo.getTabuleiro().getTabuleiro()[jogada.getLinha()][jogada.getColuna()] = null;	
					break;
				}
			}
			if(!voltouJogada) {
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
			
			//if(tabu[coluna - 1][linha - 1] != null) {
			int aux  = 0;
			boolean comeu = false;
			for (JogadasPossiveis jogadas : possiveisAtaques) {
				
				if(jogadas.getColuna() ==  coluna_saida && jogadas.getLinha() == linha_saida) {
					voltarJogada.AddComidas(possiveisComidas.get(aux).getLinha(), possiveisComidas.get(aux).getColuna());
					comeu = true;
					break;
				}
				aux++;
				
			}
			if(!comeu) {
				voltarJogada.AddComidas(-1,-1);
			}
			
			voltarJogada.AddJogadaAnterior(coluna, linha);
			voltarJogada.AddJogadaAtual( linha_saida, coluna_saida);
			
			System.out.println();
			
		
			}
			
			
			jogo.imprimirTabuleiro();
			voltouJogada = false;
		}
		teclado.close();
	}
	
}
