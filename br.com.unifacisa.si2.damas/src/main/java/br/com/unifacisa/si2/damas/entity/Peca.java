package br.com.unifacisa.si2.damas.entity;

import br.com.unifacisa.si2.damas.Cor;

public class Peca {

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

	public Cor getColor() {
		return cor;
	}

	public void setColor(Cor cor) {
		this.cor = cor;
	}
	
	
	

}

