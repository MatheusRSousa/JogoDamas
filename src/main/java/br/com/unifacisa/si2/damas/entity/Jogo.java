package br.com.unifacisa.si2.damas.entity;

import java.util.List;

public class Jogo {

	private Jogador jogador1;
	private Jogador jogador2;
	private int linhaAnterior;
	private int colunaAnterior;
	private Tabuleiro tabuleiro;
	private int qntdJogadas = 0;
	private List<JogadasPossiveis> jogadasPossiveis;
	private List<JogadasPossiveis> possiveisAtaques;
	private List<JogadasPossiveis> possiveisComidas;

	public Jogo(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.tabuleiro = tabuleiro;
	}

	
	public List<JogadasPossiveis> getJogadasPossiveis() {
		return jogadasPossiveis;
	}


	public void setJogadasPossiveis(List<JogadasPossiveis> jogadasPossiveis) {
		this.jogadasPossiveis = jogadasPossiveis;
	}


	public List<JogadasPossiveis> getPossiveisAtaques() {
		return possiveisAtaques;
	}


	public void setPossiveisAtaques(List<JogadasPossiveis> possiveisAtaques) {
		this.possiveisAtaques = possiveisAtaques;
	}


	public List<JogadasPossiveis> getPossiveisComidas() {
		return possiveisComidas;
	}


	public void setPossiveisComidas(List<JogadasPossiveis> possiveisComidas) {
		this.possiveisComidas = possiveisComidas;
	}


	public void moverPeca(Peca peca, int linhaSaida, int colunaSaida) {
		boolean jogadaPossivel = false;
		for (JogadasPossiveis jogadasPossiveis : jogadasPossiveis) {
			if(jogadasPossiveis.getLinha() == linhaSaida && jogadasPossiveis.getColuna() == colunaSaida) {
				jogadaPossivel = true;
			}
		}
		for (JogadasPossiveis possiveisAtaques : possiveisAtaques) {
			if(possiveisAtaques.getColuna() == colunaSaida && possiveisAtaques.getLinha() == linhaSaida) {
				jogadaPossivel = true;
				for (JogadasPossiveis possiveisComidas : possiveisComidas) {
					tabuleiro.getTabuleiro()[possiveisComidas.getColuna()][possiveisComidas.getLinha()] = null;
				}
			}
		}
		if (jogadaPossivel) {
			System.out.println("Jogada valida");
			tabuleiro.getTabuleiro()[colunaAnterior][linhaAnterior] = null;
			tabuleiro.getTabuleiro()[colunaSaida][linhaSaida] = peca;
			if((colunaSaida == 0 && getDaVez().getPeca().getLadoTabuleiro() == 0)||
					(colunaSaida == tabuleiro.getTamanho()-1 && getDaVez().getPeca().getLadoTabuleiro() == 1))  {
				tabuleiro.getTabuleiro()[colunaSaida][linhaSaida].setDama(true);
			}
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
					JogadaPossivel(linha, coluna);
					if (jogadasPossiveis.size() > 0 || possiveisAtaques.size() > 0) {
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
	public Jogador getDaVez() {
		return (this.qntdJogadas % 2 == 0) ? jogador1 : jogador2;
	}

	// Retorna se existe jogada possivel
	public void JogadaPossivel(int linha, int coluna) {
		JogadasPossiveis jogadas = new JogadasPossiveis(tabuleiro);
		Peca[][] tabu = tabuleiro.getTabuleiro();

		Jogador jogadorVez = getDaVez();
		if (tabu[coluna][linha].isDama()) {
			jogadas.jogadasDama(linha, coluna, jogadorVez, tabu);
		} else if (jogadorVez.getPeca().getLadoTabuleiro() == 0) {
			jogadas.getJogadaPossivelJ1(linha, coluna, jogadorVez, tabu);
		} else {
			jogadas.getJogadaPossivelJ2(linha, coluna, jogadorVez, tabu);
		}
		jogadasPossiveis = jogadas.getJogadasPossiveis();
		possiveisAtaques = jogadas.getPossiveisAtaques();
		possiveisComidas = jogadas.getPossiveisComidas();
	}


	// imprimir tabuleiro
	public void imprimirTabuleiro() {

		System.out.println("Lado do jogador: " + jogador1.getNome());
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
		System.out.println("Lado do jogador: " + jogador2.getNome());
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
		this.linhaAnterior = jogadaXanterior;
	}

	public void setJogadaYanterior(int jogadaYanterior) {
		this.colunaAnterior = jogadaYanterior;
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
