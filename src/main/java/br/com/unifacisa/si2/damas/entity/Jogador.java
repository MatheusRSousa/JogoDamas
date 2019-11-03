package br.com.unifacisa.si2.damas.entity;

public class Jogador {
	
	private String nome;
	
	private Peca peca;
	
	private int pontuacao;
	
	public Jogador(String nome, Peca peca) {
		this.nome = nome;
		this.peca = peca;
	}
	
	
	
	public int getPontuacao() {
		return pontuacao;
	}



	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}



	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
