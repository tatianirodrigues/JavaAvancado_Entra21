package model.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.BancoDeDados;
import model.entidade.Vacina;

/**
 * @author guest01
 *
 */
public class VacinaRepositorio {

	//CRUD

	//Create
	public Vacina inserir(Vacina novaVacina) {
		//Conectar no banco
		Connection conexao = BancoDeDados.getConnection();
		//Obter o Statement

		String sql = " insert into vacina (paisOrigem, estagioPesquisa, dataInicioPesquisa, nomePesquisadorResponsavel) value (?, ?, ?, ?)";

		PreparedStatement stmt = BancoDeDados.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novaVacina.getPaisOrigem());
			stmt.setInt(2, novaVacina.getEstagioPesquisa());
			stmt.setDate(3, (Date) novaVacina.getDataInicioPesquisa());
			stmt.setString(4, novaVacina.getNomePesquisadorResponsavel());
			
			stmt.execute();

			//ResultSet generatedKeys = stmt.getGeneratedKeys();
			//if (generatedKeys.next()) {
			//	int idGerado = generatedKeys.getInt(1);
			//	novoTelefone.setId(idGerado);
			//}
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar Query");
		}

		//Obter a chave gerada

		//Preencher a chave em novaVacina

		return novaVacina;
	}

	//Retrieve 
	public Vacina pesquisarPorId(int id) {
		return null;
	}

	public ArrayList<Vacina> pesquisarTodas (){

		return null;
	}

	//Update
	public Vacina atualizar(Vacina vacina) {
		return vacina;
	}

	//Delete
	public boolean excluir (int id) {

		boolean excluiu = false;

		//Conectar no banco
		Connection conexao = BancoDeDados.getConnection();
		String sql = " DELETE FROM VACINA "
				+ " WHERE ID = ? ";

		//Obter o preparedStatement
		PreparedStatement stmt = BancoDeDados.getPreparedStatement(conexao, sql);
		try {
			//Executar
			stmt.setInt(1, id);
			int registrosExcluidos = stmt.executeUpdate();
			excluiu = (registrosExcluidos > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir vacina.\nCausa: " + e.getCause());
		}

		return excluiu;
	}
}