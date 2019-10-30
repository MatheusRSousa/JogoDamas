package br.com.unifacisa.si2.damas.entity;

import java.util.ArrayList;
import java.util.List;

public class JogadasPossiveis {

	private int linha;
	private int coluna;
	private Tabuleiro tabuleiro;

	public JogadasPossiveis(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public JogadasPossiveis(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int x) {
		this.linha = x;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int y) {
		this.coluna = y;
	}

	public List<List<JogadasPossiveis>> jogadasDama(int linha, int coluna,  Jogador jogador, Peca[][] tabu) {
		List<JogadasPossiveis> jogadasPossiveis = new ArrayList<JogadasPossiveis>();
		List<List<JogadasPossiveis>> jogadas = new ArrayList<List<JogadasPossiveis>>();
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
		jogadas.add(jogadasPossiveis);
		return jogadas;
	}

	// verifica se a jogada do jogador 2 é possivel
	public List<List<JogadasPossiveis>> getJogadaPossivelJ2(int linha, int coluna, Jogador jogadorVez, Peca[][] tabu) {
		List<List<JogadasPossiveis>> listaDeJogadas = new ArrayList<List<JogadasPossiveis>>();
		List<JogadasPossiveis> jogadasPossiveis = new ArrayList<JogadasPossiveis>();
		List<JogadasPossiveis> possiveisComidas = new ArrayList<JogadasPossiveis>();
		List<JogadasPossiveis> possiveisAtaques = new ArrayList<JogadasPossiveis>();
		if (linha >= 0 && linha < tabuleiro.getTamanho() && coluna >= 0 && coluna < tabuleiro.getTamanho()) {
			if (linha + 1 < tabu.length && coluna -1 >= 0) {
				if(tabu[coluna -1][linha+1] == null) {
					jogadasPossiveis.add(new JogadasPossiveis(linha+1, coluna-1));
				}
			if(linha - 1 >= 0 && coluna - 1 >=0) {
				if(tabu[linha -1][coluna-1] == null) {
					jogadasPossiveis.add(new JogadasPossiveis(linha-1, coluna-1));
				}
			}
			}
			 if (linha -1 > 1 && coluna -1> 1 ) {
					if(tabu[coluna - 1][linha - 1] != null) {
						if(tabu[linha -1][coluna-1].getCor() != jogadorVez.getPeca().getCor() && tabu[linha -2][coluna-2] == null) {
							possiveisAtaques.add(new JogadasPossiveis(linha-2, coluna-2));
							possiveisComidas.add(new JogadasPossiveis(linha -1, coluna - 1));
						}
					}
				}
			if(linha + 1  < tabu.length && coluna - 1 > 1) {
				if(tabu[linha][coluna].getCor() != tabu[linha +1][coluna-1].getCor() && tabu[linha +2][coluna-2] == null) {
					possiveisAtaques.add(new JogadasPossiveis(linha+2, coluna-2));		
					possiveisComidas.add(new JogadasPossiveis(linha+1, coluna-1));
				}
			}
				
		}
		listaDeJogadas.add(jogadasPossiveis);
		listaDeJogadas.add(possiveisComidas);
		listaDeJogadas.add(possiveisAtaques);
		return listaDeJogadas;
	}

	// verifica se a jogada da peça que o jogador 1 escolheu é possivel
	public List<List<JogadasPossiveis>> getJogadaPossivelJ1(int linha, int coluna, Jogador jogadorVez, Peca[][] tabu) {
		List<List<JogadasPossiveis>> listaDeJogadas = new ArrayList<List<JogadasPossiveis>>();
		List<JogadasPossiveis> jogadasPossiveis = new ArrayList<JogadasPossiveis>();
		List<JogadasPossiveis> possiveisComidas = new ArrayList<JogadasPossiveis>();
		List<JogadasPossiveis> possiveisAtaques = new ArrayList<JogadasPossiveis>();
		if (linha >= 0 && linha < tabuleiro.getTamanho() && coluna >= 0 && coluna < tabuleiro.getTamanho()) {
			if (coluna > 0 && tabu[coluna + 1][linha - 1] == null){
				jogadasPossiveis.add(new JogadasPossiveis(linha-1,coluna+1));
			}
			if (linha < tabuleiro.getTamanho() && linha < tabuleiro.getTamanho() && tabu[coluna + 1][linha + 1] == null){
					jogadasPossiveis.add(new JogadasPossiveis(linha+1,coluna+1));
			}
		} if (linha >= 0 && linha + 1 < tabuleiro.getTamanho() && coluna >= 0 && coluna + 1 < tabuleiro.getTamanho()
				&& (tabu[coluna + 1][linha + 1] != null || tabu[coluna + 1][linha - 1] != null)) {
			
			if ((tabu[coluna + 2][linha + 2] == null && tabu[coluna + 1][linha + 1].getCor() != jogadorVez.getPeca().getCor())
					&& (coluna + 2 < tabu[coluna].length) || (linha - 2) >= 0) {
				possiveisAtaques.add(new JogadasPossiveis(linha+2, coluna+2));
				possiveisComidas.add(new JogadasPossiveis(linha+1, coluna+1));
				
			} if ((tabu[coluna + 2][linha - 2] == null
					&& tabu[coluna + 1][linha - 1].getCor() != jogadorVez.getPeca().getCor()) && (coluna - 2 >= 0)
					|| (linha + 2) < tabu.length) {
				possiveisAtaques.add(new JogadasPossiveis(linha-1, coluna+1));
				possiveisComidas.add(new JogadasPossiveis(linha-2, coluna+2));
			}
		}
		listaDeJogadas.add(jogadasPossiveis);
		listaDeJogadas.add(possiveisComidas);
		listaDeJogadas.add(possiveisAtaques);
		return listaDeJogadas;
	}

}
