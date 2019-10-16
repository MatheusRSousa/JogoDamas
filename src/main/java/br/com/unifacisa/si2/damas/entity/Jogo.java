package br.com.unifacisa.si2.damas.entity;


public class Jogo {

	private Jogador jogador1;
	private Jogador jogador2;
	private int jogadaXanterior;
	private int jogadaYanterior;
	private Tabuleiro tabuleiro;
	private int qntdJogadas = 0;

	public Jogo(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.tabuleiro = tabuleiro;
	}

	
	public void moverPeca(Peca peca, int x_sai, int y_sai) {
		
		if(this.isJogadaValida(x_sai, y_sai)) {
			System.out.println("Jogada valida");
			tabuleiro.getTabuleiro()[jogadaYanterior][jogadaXanterior] = null;
			tabuleiro.getTabuleiro()[y_sai][x_sai] = peca;
			qntdJogadas ++;
		}
		
		else {
			System.out.println("jogada invalida");
		}
	}
	
	
	//Escolher peça
	public Peca escolherPeca(int x, int y) {
		Peca[][] tabu = tabuleiro.getTabuleiro();
		
		Jogador jogadorVez = getDaVez();
		if(x < tabuleiro.getTamanho() && y < tabuleiro.getTamanho()) {
			if(tabu[y][x] != null) {
				if(tabu[y][x].getCor() == jogadorVez.getPeca().getCor()) {
					if(getJogadaPossivel(x, y)) {
						Peca peca = tabu[y][x];
						setJogadaXanterior(x);
						setJogadaYanterior(y);
						return peca;
					}
				}
			}
		}
		
		System.out.println();
		System.out.println("Peça inválida, escolha outra peça");
		System.out.println();
		return null;
	}

	//Retorna o jogador da vez
	private Jogador getDaVez() {
		return (this.qntdJogadas % 2 == 0) ? jogador1 : jogador2;
	}

	//Retorna se existe jogada possivel
	public boolean getJogadaPossivel(int x, int y) {
		Peca[][] tabu = tabuleiro.getTabuleiro();

		Jogador jogadorVez = getDaVez();

		if (x >= 0 && x < tabuleiro.getTamanho() && y >= 0 && y < tabuleiro.getTamanho()) {
			if (jogadorVez.getPeca().getLadoTabuleiro() == 0) {
				
				if ((y > 0 && tabu[y - 1][x + 1] == null ||( x < tabuleiro.getTamanho()
						&& x < tabuleiro.getTamanho() && tabu[y + 1][x + 1] == null))) {
					return true;
				}
			}else {
				if ((x > 0 && y > 0 && tabu[y - 1][x - 1] == null)
						|| (y > 0 && x < tabuleiro.getTamanho() && tabu[y - 1][x + 1] == null)) {
					return true;
				}
			}
		}
		return false;
	}
	
	//Verifica se a jogada é válida
	private boolean isJogadaValida(int x, int y) {
		Peca[][] tabu = tabuleiro.getTabuleiro();
		
		if(y % 2 ==0 && x % 2 != 0) {
			if(tabu[y][x] == null) {
				return true;
			}
		}else if(y % 2 != 0 && x % 2 == 0) {
			
			if(tabu[y][x] == null) {
				
				return true;
			}
		}
		return false;
	}

	//imprimir tabuleiro
	public void imprimirTabuleiro() {
		System.out.print("    ");
		for (int i = 0; i < tabuleiro.getTabuleiro().length; i++) {
			System.out.print(i + "    ");
			
		}
		System.out.println();
		for (int i = 0; i < tabuleiro.getTabuleiro().length; i++) {
			System.out.print(i+" ");
			for (int j = 0; j < tabuleiro.getTabuleiro().length; j++) {
				
				if(tabuleiro.getTabuleiro()[i][j] == null) {
					System.out.print("     ");
				} 
				else {
					System.out.print(tabuleiro.getTabuleiro()[i][j].getCor());
			}
			}
			System.out.println();
		}
	}

	//Retorna a quantidade de perça que o jogador ainda tem no tabuleiro
	public int qtdPecaPorJogador(Jogador jogador) {
		Peca[][] tabu = tabuleiro.getTabuleiro();
		
		int qtdPeca = 0;
		
		for (int i = 0; i < tabu.length; i++) {
			for (int j = 0; j < tabu.length; j++) {
				if(tabu[i][j].getCor() == jogador.getPeca().getCor()) {
					qtdPeca += 1;
				}
			}
		}
		return qtdPeca;
	}
	public void setJogadaXanterior(int jogadaXanterior) {
		this.jogadaXanterior = jogadaXanterior;
	}
	public void setJogadaYanterior(int jogadaYanterior) {
		this.jogadaYanterior = jogadaYanterior;
	}
		
//	//Verifica se o jogo acabou
//	public boolean acabouJogo() {
//		
//		boolean acabou = false;
//		
//		int pecaJo1 = this.qtdPecaPorJogador(jogador1);
//		
//		int pecaJo2 = this.qtdPecaPorJogador(jogador2);
//		
//		if(pecaJo1 == 0 || pecaJo2 == 0) {
//			acabou = true;
//		}
//		
//		return acabou;
//	}
}
