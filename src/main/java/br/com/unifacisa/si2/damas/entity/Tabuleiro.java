package br.com.unifacisa.si2.damas.entity;

public class Tabuleiro {
	
	private static final int LADO_NORTE = 0;
	
	private static final int LADO_SUL = 1;

	private int tamanho = -1;
	
	Peca[][] tabuleiro;
	
	//usar o singleton (tabuleiro unico)
	public Tabuleiro(Jogador jogador1, Jogador jogador2, int tamanho) {
		jogador1.getPeca().setLadoTabuleiro(LADO_NORTE);
		jogador2.getPeca().setLadoTabuleiro(LADO_SUL);
		setTamanho(tamanho);
		tabuleiro = new Peca[tamanho][tamanho];
		
		for (int i = 0; i <	 tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				
				if(i < (tamanho/2) -1) {
					if(i%2 != 0) {
						if(j % 2 == 0) {
							tabuleiro[i][j] = jogador1.getPeca(); 
						}
					}
					else if(i%2 == 0){
						if(j % 2 != 0) {
							tabuleiro[i][j] = jogador1.getPeca();
						}
					}
					else {
						tabuleiro[i][j] = null;
					}
				}
				
				else if(i > tamanho/2){
					if(i%2 != 0) {
						if(j % 2 == 0) {
							tabuleiro[i][j] = jogador2.getPeca(); 
						}
					}
					else if(i%2 == 0){
						if(j % 2 != 0) {
							tabuleiro[i][j] = jogador2.getPeca();
						}
					}
				}
				else {
					tabuleiro[i][j] =null;
				}
			}
		}
	}
	
	public Peca[][] getTabuleiro(){
		return tabuleiro;
	}

	public int getTamanho() {
		return tamanho;
	}


	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
}
