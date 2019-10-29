package br.com.unifacisa.si2.damas.entity;

import java.util.List;

public class Jogo {

	private Jogador jogador1;
	private Jogador jogador2;
	private int jogadaXanterior;
	private int jogadaYanterior;
	private Tabuleiro tabuleiro;
	private int qntdJogadas = 0;

	public Jogo(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.tabuleiro = tabuleiro;
	}

	public void moverPeca(Peca peca, int linhaSaida, int colunaSaida) {

		if (this.isJogadaValida(linhaSaida, colunaSaida)) {
			System.out.println("Jogada valida");
			tabuleiro.getTabuleiro()[jogadaYanterior][jogadaXanterior] = null;
			tabuleiro.getTabuleiro()[colunaSaida][linhaSaida] = peca;
			qntdJogadas++;
		} else {
			System.out.println("jogada invalida");
		}
	}

	// Escolher peça
	public Peca escolherPeca(int linha, int coluna) {
		Peca[][] tabu = tabuleiro.getTabuleiro();
		Jogador jogadorVez = getDaVez();
		if (linha < tabuleiro.getTamanho() && coluna < tabuleiro.getTamanho()) {
			Peca peca = tabu[coluna][linha];
			if (peca != null) {
				if (peca.getCor() == jogadorVez.getPeca().getCor()) {
					if (getJogadaPossivel(linha, coluna).size() > 0) {
						setJogadaXanterior(linha);
						setJogadaYanterior(coluna);
						return peca;
					}
				}
			}
		}
		System.out.println();
		System.out.println("Peça inválida, escolha outra peça");
		System.out.println();
		return null;
	}

	// Retorna o jogador da vez
	private Jogador getDaVez() {
		return (this.qntdJogadas % 2 == 0) ? jogador1 : jogador2;
	}

	// Retorna se existe jogada possivel
	private List<JogadasPossiveis> getJogadaPossivel(int linha, int coluna) {
		JogadasPossiveis jogadasPossiveis = new JogadasPossiveis(tabuleiro);

		Peca[][] tabu = tabuleiro.getTabuleiro();

		Jogador jogadorVez = getDaVez();

		if (jogadorVez.getPeca().getLadoTabuleiro() == 0) {
			return jogadasPossiveis.getJogadaPossivelJ1(linha, coluna, jogadorVez, tabu);
		} else {
			return jogadasPossiveis.getJogadaPossivelJ2(linha, coluna, jogadorVez, tabu);
		}
	}

	// Verifica se a jogada é válida
	private boolean isJogadaValida(int linha, int coluna) {
		Peca[][] tabu = tabuleiro.getTabuleiro();

		if (coluna % 2 == 0 && linha % 2 != 0) {
			if (tabu[coluna][linha] == null) {
				return true;
			}
		} else if (coluna % 2 != 0 && linha % 2 == 0) {
			if (tabu[coluna][linha] == null) {
				return true;
			}
		}
		return false;
	}

	// imprimir tabuleiro
	public void imprimirTabuleiro() {
		System.out.print("    ");
		for (int coluna = 0; coluna < tabuleiro.getTabuleiro().length; coluna++) {
			System.out.print(coluna + "    ");
		}
		System.out.println();
		for (int coluna = 0; coluna < tabuleiro.getTabuleiro().length; coluna++) {
			System.out.print(coluna + " ");
			for (int linha = 0; linha < tabuleiro.getTabuleiro().length; linha++) {

				if (tabuleiro.getTabuleiro()[coluna][linha] == null) {
					System.out.print("     ");
				} else {
					System.out.print(tabuleiro.getTabuleiro()[coluna][linha].getCor());
				}
			}
			System.out.println();
		}
	}

	// Retorna a quantidade de perça que o jogador ainda tem no tabuleiro
	public int qtdPecaPorJogador(Jogador jogador) {
		Peca[][] tabu = tabuleiro.getTabuleiro();

		int qtdPeca = 0;

		for (int coluna = 0; coluna < tabu.length; coluna++) {
			for (int linha = 0; linha < tabu.length; linha++) {
				if (tabu[coluna][linha].getCor() == jogador.getPeca().getCor()) {
					qtdPeca += 1;
				}
			}
		}
		return qtdPeca;
	}

	public void setJogadaXanterior(int jogadaXanterior) {
		this.jogadaXanterior = jogadaXanterior;
	}

	public void setJogadaYanterior(int jogadaYanterior) {
		this.jogadaYanterior = jogadaYanterior;
	}

//	//Verifica se o jogo acabou
//	public boolean acabouJogo() {
//		
//		boolean acabou = false;
//		
//		int pecaJo1 = this.qtdPecaPorJogador(jogador1);
//		
//		int pecaJo2 = this.qtdPecaPorJogador(jogador2);
//		
//		if(pecaJo1 == 0 || pecaJo2 == 0) {
//			acabou = true;
//		}
//		
//		return acabou;
//	}
}
