package br.com.unifacisa.si2.damas.entity;

import br.com.unifacisa.si2.damas.strategy.CriarTabuleiro;

public class Tabuleiro {

	
	Peca[][] tabuleiro;
	
	public Tabuleiro(CriarTabuleiro tabuleiro,Jogador jogador1, Jogador jogador2) {
		this.tabuleiro = tabuleiro.criarTabuleiro(jogador1, jogador2);
		
	}
	
	public Tabuleiro(Peca[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	public Peca[][] getTabuleiro(){
		return tabuleiro;
	}
	public void setTabuleiro(Peca[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
}
