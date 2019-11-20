package br.com.unifacisa.si2.damas.entity;

import java.util.Arrays;
import java.util.List;

import br.com.unifacisa.si2.damas.memento.VoltarJogada;

public class Jogo {

	private static final int VALOR_ASCII_CONVERSAO = 97;

	private Jogador jogador1;
	private Jogador jogador2;
	private int linhaAnterior;
	private int colunaAnterior;
	private Jogador vencedor;
	private Tabuleiro tabuleiro;
	private int qntdJogadas = 0;
	private List<JogadasPossiveis> jogadasPossiveis;
	private List<JogadasPossiveis> possiveisAtaques;
	private List<JogadasPossiveis> possiveisComidas;
	private VoltarJogada voltarJogada;
	private int qntdJogadasEmpate;

	public Jogo(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro, VoltarJogada voltarJogada) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.tabuleiro = tabuleiro;
		this.voltarJogada = voltarJogada;
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}

	public List<JogadasPossiveis> getJogadasPossiveis() {
		return jogadasPossiveis;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
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

	public void setJogadaXanterior(int jogadaXanterior) {
		this.linhaAnterior = jogadaXanterior;
	}

	public void setJogadaYanterior(int jogadaYanterior) {
		this.colunaAnterior = jogadaYanterior;
	}

	public int getQntdJogadas() {
		return qntdJogadas;
	}

	public void diminuiJogada() {
		qntdJogadas--;
	}

	public Jogador getVencedor() {
		return vencedor;
	}

	public void moverPeca(Peca peca, int linhaSaida, String letra) {
		letra = letra.toLowerCase();
		char letra1 = letra.charAt(0);
		int colunaSaida = letra1 - VALOR_ASCII_CONVERSAO;
		boolean jogadaPossivel = false;
		int aux = 0;
		boolean comeu = false;
		for (JogadasPossiveis jogadasPossiveis : jogadasPossiveis) {
			if (jogadasPossiveis.getLinha() == linhaSaida && jogadasPossiveis.getColuna() == colunaSaida) {
				jogadaPossivel = true;
			}
		}
		for (JogadasPossiveis possiveisAtaques : possiveisAtaques) {
			if (possiveisAtaques.getColuna() == colunaSaida && possiveisAtaques.getLinha() == linhaSaida) {
				voltarJogada.AddComidas(possiveisComidas.get(aux).getLinha(), possiveisComidas.get(aux).getColuna());
				comeu = true;
				jogadaPossivel = true;

				for (int i = 0; i < possiveisComidas.size();) {
					if (linhaSaida > possiveisComidas.get(i).getLinha()) {
						tabuleiro.getTabuleiro()[possiveisComidas.get(i).getColuna()][possiveisComidas.get(i)
								.getLinha()] = null;
						getDaVez().setPontuacao(getDaVez().getPontuacao() + 1);
						break;
					} else {
						tabuleiro.getTabuleiro()[possiveisComidas.get(i).getColuna()][possiveisComidas.get(i)
								.getLinha()] = null;
						getDaVez().setPontuacao(getDaVez().getPontuacao() + 1);
						break;
					}
				}
			}
			aux++;
		}
		if (jogadaPossivel) {
			if (!comeu) {
				voltarJogada.AddComidas(-1, -1);
			}
			voltarJogada.AddJogadaAnterior(colunaAnterior, linhaAnterior);
			voltarJogada.AddJogadaAtual(linhaSaida, colunaSaida);
			System.out.println("Jogada valida");
			tabuleiro.getTabuleiro()[colunaAnterior][linhaAnterior] = null;
			tabuleiro.getTabuleiro()[colunaSaida][linhaSaida] = peca;
			if ((colunaSaida == tabuleiro.getTabuleiro().length - 1 && getDaVez().getPeca().getLadoTabuleiro() == 0)
					|| (colunaSaida == 0 && getDaVez().getPeca().getLadoTabuleiro() == 1)) {
				tabuleiro.getTabuleiro()[colunaSaida][linhaSaida].setDama(true);
			}
			qntdJogadas++;
		} else {
			System.out.println("jogada invalida");
		}
	}

	// Escolher pe�a
	public Peca escolherPeca(int linha, String letra) {
		letra = letra.toLowerCase();
		char letra1 = letra.charAt(0);
		int coluna = letra1 - VALOR_ASCII_CONVERSAO;
		Peca[][] tabu = tabuleiro.getTabuleiro();
		Jogador jogadorVez = getDaVez();
		if (linha < tabu.length && coluna < tabu.length) {
			Peca peca = tabu[coluna][linha];
			if (peca != null) {
				if (peca.getCor() == jogadorVez.getPeca().getCor()) {
					JogadaPossivel(linha, letra);
					if (jogadasPossiveis.size() > 0 || possiveisAtaques.size() > 0) {
						setJogadaXanterior(linha);
						setJogadaYanterior(coluna);
						return peca;
					}
				}
			}
		}
		System.out.println();
		System.out.println("Pe�a inv�lida, escolha outra pe�a");
		System.out.println();
		return null;
	}

	// Retorna o jogador da vez
	public Jogador getDaVez() {
		return (this.qntdJogadas % 2 == 0) ? jogador2 : jogador1;
	}

	// Retorna se existe jogada possivel
	public void JogadaPossivel(int linha, String letra) {
		letra = letra.toLowerCase();
		char letra1 = letra.charAt(0);
		int coluna = letra1 - VALOR_ASCII_CONVERSAO;
		JogadasPossiveis jogadas = new JogadasPossiveis();
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
		int tamanhoPrintSigla = tabuleiro.getTabuleiro().length * 8 - 5;
		char sigla = '-';
		char sigla2 = '_';
		char[] arrayDeSiglas = new char[tamanhoPrintSigla];
		char[] arrayDeSiglas2 = new char[tamanhoPrintSigla];
		Arrays.fill(arrayDeSiglas, sigla);
		Arrays.fill(arrayDeSiglas2, sigla2);
		String stringComAsSiglas = new String(arrayDeSiglas);
		String stringComAsSiglas2 = new String(arrayDeSiglas2);
		System.out.println();
		System.out.println("Lado do jogador: " + jogador1.getNome() + ", pontos: " + jogador1.getPontuacao());
		System.out.print("     ");
		for (int coluna = 0; coluna < tabuleiro.getTabuleiro().length; coluna++) {
			System.out.print(coluna + "      ");
		}
		System.out.println();
		System.out.println(stringComAsSiglas2);
		for (int coluna = 0; coluna < tabuleiro.getTabuleiro().length; coluna++) {
			int num = coluna + VALOR_ASCII_CONVERSAO;
			char letra = (char) num;
			String.valueOf(letra);
			System.out.print(letra + "|");
			for (int linha = 0; linha < tabuleiro.getTabuleiro().length; linha++) {
				System.out.print("|");
				if (tabuleiro.getTabuleiro()[coluna][linha] == null) {
					System.out.print("      ");
				} else {
					if (linha == 0) {
						System.out.print("");
					}
					System.out.print(tabuleiro.getTabuleiro()[coluna][linha].getCor());
				}
			}
			System.out.println("|");
			System.out.println(stringComAsSiglas);
		}

		System.out.println("Lado do jogador: " + jogador2.getNome() + ", pontos: " + jogador2.getPontuacao());
	}

	public void voltarJogada() {
		if (getQntdJogadas() == 0) {
			System.out.println("Impossivel Voltar Jogada");

		} else {
			VoltarJogada jogada = voltarJogada.getJogadaAtuais();
			VoltarJogada volta = voltarJogada.getJogadaAnterior();
			VoltarJogada comidas = voltarJogada.getPecasComidas();

			if (comidas != null) {
				getTabuleiro().getTabuleiro()[comidas.getLinha()][comidas.getColuna()] = getDaVez().getPeca();
			}
			System.out.println(volta.getColuna() + " " + volta.getLinha());

			diminuiJogada();
			getTabuleiro().getTabuleiro()[volta.getLinha()][volta.getColuna()] = getDaVez().getPeca();
			getTabuleiro().getTabuleiro()[jogada.getLinha()][jogada.getColuna()] = null;
		}
	}

	// Verifica se o jogo acabou
	public boolean acabouJogo() {
		boolean acabou = false;
		int pecaJo1 = this.qtdPecaPorJogador(jogador1);
		int pecaJo2 = this.qtdPecaPorJogador(jogador2);
		if (pecaJo1 == 0) {
			acabou = true;
			vencedor = jogador2;
		} else if (pecaJo2 == 0) {
			acabou = true;
			vencedor = jogador1;
		}
		return acabou;
	}

	public boolean empatou() {
		int totalPecas = qtdPecaPorJogador(jogador1) + qtdPecaPorJogador(jogador2);
		int qntdDamasJogador1 = qtdDamaPorJogador(jogador1);
		int qntdDamasJogador2 = qtdDamaPorJogador(jogador2);
		if (totalPecas <= 4 && (qntdDamasJogador1 == 1 || qntdDamasJogador1 == 2)
				&& (qntdDamasJogador2 == 1 || qntdDamasJogador2 == 2)) {
			if (qntdJogadasEmpate == 5) {
				return true;
			}
			qntdJogadasEmpate++;
		}

		return false;
	}

	// Retorna a quantidade de per�a que o jogador ainda tem no tabuleiro
	private int qtdPecaPorJogador(Jogador jogador) {
		Peca[][] tabu = tabuleiro.getTabuleiro();
		int qtdPeca = 0;
		for (int coluna = 0; coluna < tabu.length; coluna++) {
			for (int linha = 0; linha < tabu.length; linha++) {
				if (tabu[coluna][linha] != null) {
					if (tabu[coluna][linha].getCor() == jogador.getPeca().getCor()) {
						qtdPeca += 1;
					}
				}
			}
		}
		return qtdPeca;
	}

	public int qtdDamaPorJogador(Jogador jogador) {
		Peca[][] tabu = tabuleiro.getTabuleiro();

		int qtdPeca = 0;

		for (int coluna = 0; coluna < tabu.length; coluna++) {
			for (int linha = 0; linha < tabu.length; linha++) {
				if (tabu[coluna][linha] != null) {
					if (tabu[coluna][linha].getCor() == jogador.getPeca().getCor() && tabu[coluna][linha].isDama()) {
						qtdPeca += 1;
					}
				}
			}
		}
		return qtdPeca;
	}
}
