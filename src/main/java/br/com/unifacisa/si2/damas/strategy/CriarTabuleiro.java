package br.com.unifacisa.si2.damas.strategy;

import br.com.unifacisa.si2.damas.entity.Jogador;
import br.com.unifacisa.si2.damas.entity.Peca;

public interface CriarTabuleiro {
	
	
	public Peca[][] criarTabuleiro(Jogador jogador1 , Jogador jogador2);

}
