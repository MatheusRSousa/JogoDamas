package br.com.unifacisa.si2.damas.memento;



import java.util.Stack;

public class VoltarJogada {
	
	private int coluna;
	
	private int linha;
	
	 
	private Stack<VoltarJogada> jogadasAnteriores = new Stack<VoltarJogada>();
	
	private Stack<VoltarJogada> jogadasAtuais = new Stack<VoltarJogada>();
	
	private Stack<VoltarJogada> PecasComidas = new Stack<VoltarJogada>();
		
	
	public VoltarJogada() {
	}
	public VoltarJogada(int coluna, int linha) {
		this.coluna = coluna;
		this.linha = linha;
	}
	
	public void AddJogadaAnterior(int linhaAnterior, int colunaAnterior) {
		VoltarJogada aux = new VoltarJogada(colunaAnterior, linhaAnterior);
		jogadasAnteriores.push(aux);
	}
	
	public void AddJogadaAtual(int linhaAtual, int colunaAtual) {
		jogadasAtuais.push(new VoltarJogada(linhaAtual, colunaAtual));
	}
	
	public void AddComidas(int linhaAtual, int colunaAtual) {
		if(linhaAtual!= -1) {
			PecasComidas.push(new VoltarJogada(linhaAtual, colunaAtual));	
		}else {
			PecasComidas.push(null);
		}
		
	}
	
	
	public VoltarJogada getJogadaAnterior() {
		return jogadasAnteriores.pop();
	}

	public VoltarJogada getJogadaAtuais() {
		return jogadasAtuais.pop();
	}
	public VoltarJogada getPecasComidas() {
		return PecasComidas.pop();
	}
	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}
	
	
}
