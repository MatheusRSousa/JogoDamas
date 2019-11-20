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
		System.out.println("Conectando ao banco...");
		try {
			Connection conexao = DriverManager.getConnection(URL, user, pwd);
			System.out.println("Conectado!");
			
			String sql = "insert into jogo " + "(confronto, Vencedor)" + " values (?,?)";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
		
			stmt.setString(1, jogador1.getNome() + " X "+ jogador2.getNome());
			stmt.setString(2, vencedor == null ? " Empate " : vencedor.getNome());
			
			System.out.println("Salvando o confronto...");
			stmt.execute();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
