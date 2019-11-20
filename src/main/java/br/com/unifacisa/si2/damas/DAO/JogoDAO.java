package br.com.unifacisa.si2.damas.DAO;

import br.com.unifacisa.si2.damas.entity.Jogador;

public interface JogoDAO {
	
	void insertConfronto(Jogador jogador1, Jogador jogador2, Jogador vencedor);
}
