package br.com.unifacisa.si2.damas.entity;

import java.util.ArrayList;
import java.util.List;

public class JogadasPossiveis {

	private int x;
	private int y;
	private Tabuleiro tabuleiro;

	public JogadasPossiveis(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public JogadasPossiveis(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public List<JogadasPossiveis> jogadasDama(int linha, int coluna, Peca[][] tabu, Jogador jogador) {
		ArrayList<JogadasPossiveis> jogadasPossiveis = new ArrayList<JogadasPossiveis>();
		int direita = linha;
		int esquerda = linha;
		int baixo = coluna;
		int cima = coluna;
		while (baixo < tabu.length && direita < tabu.length) {
			baixo++;
			direita++;
			if (tabu[baixo][direita] == null) {
				jogadasPossiveis.add(new JogadasPossiveis(linha, coluna));
			} else if (tabu[baixo][direita].getCor() == jogador.getPeca().getCor()) {
				break;
			}
		}
		esquerda = linha;
		baixo = coluna;
		while (baixo < tabu.length && esquerda > 0) {
			baixo++;
			esquerda--;
			if (tabu[baixo][esquerda] == null) {
				jogadasPossiveis.add(new JogadasPossiveis(esquerda, baixo));
			} else if (tabu[baixo][esquerda].getCor() == jogador.getPeca().getCor()) {
				break;
			}
		}
		direita = linha;
		esquerda = linha;
		baixo = coluna;
		while (cima > 0 && esquerda > 0) {
			cima--;
			esquerda--;
			if (tabu[cima][esquerda] == null) {
				jogadasPossiveis.add(new JogadasPossiveis(esquerda, cima));
			} else if (tabu[cima][esquerda].getCor() == jogador.getPeca().getCor()) {
				break;
			}
		}
		direita = linha;
		esquerda = linha;
		cima = coluna;
		while (baixo < tabu.length && esquerda > 0) {
			cima--;
			direita++;
			if (tabu[cima][direita] == null) {
				jogadasPossiveis.add(new JogadasPossiveis(direita, cima));
			} else if (tabu[cima][direita].getCor() == jogador.getPeca().getCor()) {
				break;
			}
		}
		return jogadasPossiveis;
	}

	// verifica se a jogada do jogador 1 é possivel
	public List<JogadasPossiveis> getJogadaPossivelJ2(int linha, int coluna, Jogador jogadorVez, Peca[][] tabu) {
		List<JogadasPossiveis> jogadasPossiveis = new ArrayList<JogadasPossiveis>();
		if ((linha > 0 && coluna > 0 && tabu[coluna - 1][linha - 1] == null)
				|| (coluna > 0 && linha < tabuleiro.getTamanho() && tabu[coluna - 1][linha + 1] == null)) {
			jogadasPossiveis.add(new JogadasPossiveis(linha, coluna));
		if ((tabu[coluna - 2][linha - 2] == null
					&& tabu[coluna - 1][linha - 1].getCor() != jogadorVez.getPeca().getCor()) && (coluna - 2 >= 0)
					|| (linha - 2) >= 0) {
				jogadasPossiveis.add(new JogadasPossiveis(linha, coluna));
		} else if ((tabu[coluna - 2][linha + 2] == null
				&& tabu[coluna - 1][linha + 1].getCor() == jogadorVez.getPeca().getCor())) {
			jogadasPossiveis.add(new JogadasPossiveis(linha, coluna));
		}
	}
	return jogadasPossiveis;
	}

	// verifica se a jogada da peça que o jogador 1 escolheu é possivel
	public List<JogadasPossiveis> getJogadaPossivelJ1(int linha, int coluna, Jogador jogadorVez, Peca[][] tabu) {
		List<JogadasPossiveis> jogadasPossiveis = new ArrayList<JogadasPossiveis>();
		if (linha >= 0 && linha < tabuleiro.getTamanho() && coluna >= 0 && coluna < tabuleiro.getTamanho()) {
			if (((coluna > 0 && tabu[coluna - 1][linha + 1] == null)
					|| (linha < tabuleiro.getTamanho() && linha < tabuleiro.getTamanho()
							&& tabu[coluna + 1][linha + 1] == null)
					|| (coluna > 1 && tabu[coluna - 2][linha + 2] == null))) {
				jogadasPossiveis.add(new JogadasPossiveis(linha, coluna));
			}
		} else if (linha >= 0 && linha + 1 < tabuleiro.getTamanho() && coluna >= 0
				&& coluna + 1 < tabuleiro.getTamanho()) {
			if ((tabu[coluna + 2][linha + 2] == null
					&& tabu[coluna + 1][linha + 1].getCor() != jogadorVez.getPeca().getCor())
					&& (coluna + 2 < tabu[coluna].length) || (linha - 2) <= 0) {
				jogadasPossiveis.add(new JogadasPossiveis(linha, coluna));
			} else if ((tabu[coluna + 2][linha - 2] == null
					&& tabu[coluna + 1][linha - 1].getCor() != jogadorVez.getPeca().getCor()) && (coluna - 2 <= 0)
					|| (linha + 2) < tabu.length) {
				jogadasPossiveis.add(new JogadasPossiveis(linha, coluna));
			}
		}
		return jogadasPossiveis;
	}

}
