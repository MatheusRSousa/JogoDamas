package br.com.unifacisa.si2.damas.memento;

import java.util.ArrayList;
import java.util.List;

import br.com.unifacisa.si2.damas.entity.Tabuleiro;

public class VoltarJogada {
	private List<Tabuleiro> listaDeJogadas; 
	private int tamanho = -1;
	
	public VoltarJogada() {
		listaDeJogadas =  new ArrayList<Tabuleiro>();
	}
	
	public List<Tabuleiro> getListaDeJogadas() {
		return listaDeJogadas;
	}
	public void AddJogada(Tabuleiro tabuleiro) {
		Tabuleiro tabuleiro1 = tabuleiro;
		getListaDeJogadas().add(tabuleiro1);
		tamanho++;
	}
	
	public Tabuleiro voltaJogada(){
		Tabuleiro tabuleiro = getListaDeJogadas().get(tamanho-1);
		getListaDeJogadas().remove(tamanho);
		tamanho--;
		return tabuleiro;
	}
}
