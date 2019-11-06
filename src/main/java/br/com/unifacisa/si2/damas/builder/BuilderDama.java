package br.com.unifacisa.si2.damas.builder;



import br.com.unifacisa.si2.damas.Cor;
import br.com.unifacisa.si2.damas.entity.Jogador;
import br.com.unifacisa.si2.damas.entity.Jogo;
import br.com.unifacisa.si2.damas.entity.Peca;
import br.com.unifacisa.si2.damas.entity.Tabuleiro;
import br.com.unifacisa.si2.damas.memento.VoltarJogada;
import br.com.unifacisa.si2.damas.strategy.TabuleiroGrande;
import br.com.unifacisa.si2.damas.strategy.TabuleiroMedio;
import br.com.unifacisa.si2.damas.strategy.TabuleiroPequeno;

public class BuilderDama{

	private Tabuleiro tabuleiro;

	private Jogador jogador1;

	private Jogador jogador2;

	private Peca pecajogador1;

	private Peca pecaJogador2;

	private Jogo jogo;
	
	private VoltarJogada jogadasAnteriores;

	public BuilderDama(int tipoTabuleiro,
			String nomeJogador1,
			String nomeJogador2,
			int corPecaJogador1)  throws Exception {
		
		jogadasAnteriores = new VoltarJogada();

		switch (corPecaJogador1) {
		case 1:
			pecajogador1 = new Peca(false, Cor.BRANCO);
			pecaJogador2 = new Peca(false, Cor.PRETOO);
			break;
		case 2:
			pecajogador1 = new Peca(false, Cor.PRETOO);
			pecaJogador2 = new Peca(false, Cor.BRANCO);
			break;
		default:
			System.out.println("Digite um numero Valido");
			break;
		}

		if (pecajogador1 == null) {
			throw new Exception("Cor da peca invalida");
		}

		jogador1 = new Jogador(nomeJogador1, pecajogador1);

		jogador2 = new Jogador(nomeJogador2, pecaJogador2);

		switch (tipoTabuleiro) {
		case 1:
			tabuleiro = new Tabuleiro(new TabuleiroPequeno(), jogador1, jogador2);
			jogadasAnteriores.AddJogada(tabuleiro);
			break;
		case 2:
			tabuleiro = new Tabuleiro(new TabuleiroMedio(), jogador1, jogador2);
			jogadasAnteriores.AddJogada(tabuleiro);
			break;
		case 3:
			tabuleiro = new Tabuleiro(new TabuleiroGrande(), jogador1, jogador2);
			jogadasAnteriores.AddJogada(tabuleiro);
			break;
		default:
			System.out.println("Digite um numero Valido");
			break;
		}
		
		if(tabuleiro == null) {
			throw new Exception("Tipo do tabuleiro invalido");
		}
		
		jogo = new Jogo(jogador1, jogador2, tabuleiro);

	}

	public Jogo getJogo() throws Exception {
		if(jogo == null) {
			throw new Exception("Jogo invalido");
		}
		return jogo;
	}

	public VoltarJogada getJogadasAnteriores() {
		return jogadasAnteriores;
	}

}
