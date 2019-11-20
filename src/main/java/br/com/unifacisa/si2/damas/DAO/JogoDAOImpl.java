package br.com.unifacisa.si2.damas.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.unifacisa.si2.damas.entity.Jogador;

public class JogoDAOImpl implements JogoDAO{

	@Override
	public void insertConfronto(Jogador jogador1, Jogador jogador2, Jogador vencedor) {
		String URL = "jdbc:mysql://localhost/damas?useTimezone=true&serverTimezone=UTC";
		String user = "root";
		String pwd = "";
		System.out.println("Salvando confronto...");
		try {
			Connection conexao = DriverManager.getConnection(URL, user, pwd);
			String sql = "insert into jogo " + "(confronto, Vencedor)" + " values (?,?)";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
		
			stmt.setString(1, jogador1.getNome() + " X "+ jogador2.getNome());
			stmt.setString(2, vencedor == null ? " Empate " : vencedor.getNome());
			
		
			stmt.execute();
			conexao.close();

			System.out.println("Salvo com sucesso!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
