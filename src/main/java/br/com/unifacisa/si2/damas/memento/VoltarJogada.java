package br.com.unifacisa.si2.damas.memento;


import java.util.List;
import java.util.Stack;

import br.com.unifacisa.si2.damas.entity.Peca;

public class VoltarJogada {
	
	private int coluna;
	
	private int linha;
	 
	private Stack<VoltarJogada> jogadasAnteriores;
	
	private Stack<VoltarJogada> jogadasAtuais;
	
		
	public VoltarJogada(int coluna, int linha) {
		this.coluna = coluna;
		this.linha = linha;
	}
	
	public void AddJogadaAnterior(int linhaAnterior, int colunaAnterior) {
		jogadasAnteriores.add(new VoltarJogada(colunaAnterior, linhaAnterior));
	}
	
	public void AddJogadaAtual(int linhaAtual, int colunaAtual) {
		jogadasAtuais.add(new VoltarJogada(linhaAtual, colunaAtual));
	}
	
	public	 voltaJogada(){
		
	}
}
