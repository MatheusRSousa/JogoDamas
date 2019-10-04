package br.com.unifacisa.si2.damas.entity;

public class Jogo {

	private Jogador jogador1;
	private Jogador jogador2;
	private Tabuleiro tabuleiro;
	private int qntdJogadas = 0;

	public Jogo(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.tabuleiro = tabuleiro;
	}
	
	private Jogador getDaVez() {
		return (this.qntdJogadas % 2 == 0) ? jogador1 : jogador2;
	}
}
