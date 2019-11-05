package br.com.unifacisa.si2.damas.memento;

import java.util.ArrayList;
import java.util.List;

import br.com.unifacisa.si2.damas.entity.Peca;

public class VoltarJogada {
	private List<Peca[][]> listaDeJogadas  = new ArrayList<Peca[][]>();
	private int tamanho = -2;
	public List<Peca[][]> getListaDeJogadas() {
		return listaDeJogadas;
	}
	public void AddJogada(Peca[][] tabuleiro) {
		listaDeJogadas.add(tabuleiro);
		tamanho++;
	}
	
	public Peca[][] voltaJogada(){
		Peca[][] tabuleiro = listaDeJogadas.get(tamanho);
		listaDeJogadas.remove(tamanho);
		tamanho--;
		return tabuleiro;
	}
}
