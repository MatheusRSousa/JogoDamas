package br.com.unifacisa.si2.damas.entity;

import br.com.unifacisa.si2.damas.enuns.Cor;

public class Peca {

	private int ladoTabuleiro;
	
	private boolean isDama;
	
	private Cor cor;
	
	public Peca(boolean isDama, Cor cor) {
		this.cor = cor;
		this.isDama = isDama;
	}
	
	public boolean isDama() {
		return isDama;
	}

	public void setDama(boolean isDama) {
		this.isDama = isDama;
	}


	public int getLadoTabuleiro() {
		return ladoTabuleiro;
	}

	public void setLadoTabuleiro(int ladoTabuleiro) {
		this.ladoTabuleiro = ladoTabuleiro;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}
	
	
	

}

