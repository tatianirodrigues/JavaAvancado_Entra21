package model.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BancoDeDados;
import model.entidade.Pessoa;
import model.entidade.Vacina;

/**
 * @author guest01
 *
 */
public class VacinaRepositorio {

	// CRUD

	// Create
	public Vacina inserir(Vacina novaVacina) {
		
		// Conectar no banco --> igual para todos
		Connection conexao = BancoDeDados.getConnection();
		String sql = " insert into vacina (paisOrigem, estagioPesquisa, dataInicioPesquisa, idPesquisador) value (?, ?, ?, ?) ";

		// Obter o PreparedStatement
		PreparedStatement stmt = BancoDeDados.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setString(1, novaVacina.getPaisOrigem());
			stmt.setInt(2, novaVacina.getEstagioPesquisa());
			stmt.setDate(3, new Date(novaVacina.getDataInicioPesquisa().getTime())); // (date) ta convertendo pra data
																						// do sql
			stmt.setInt(4, novaVacina.getPesquisadorResponsavel().getId());

			stmt.execute();

			// Obter a chave gerada
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int idGerado = generatedKeys.getInt(1);
				// Preencher a chave em novaVacina
				novaVacina.setId(idGerado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar Query\ncausa:" + e.getMessage());
		} finally {
			BancoDeDados.closePreparedStatement(stmt);
			BancoDeDados.closeConnection(conexao);
		}
		return novaVacina;
	}

	// Retrieve
	public Vacina pesquisarPorId(int id) {

		Vacina vacinaBuscada = null;
		Connection conexao = BancoDeDados.getConnection();
		String sql = " SELECT * FROM VACINA " + " WHERE idVacina = ? ";

		PreparedStatement stmt = BancoDeDados.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();

			if (resultado.next()) {
				vacinaBuscada = new Vacina();

				vacinaBuscada.setId(resultado.getInt("idVacina"));
				vacinaBuscada.setEstagioPesquisa(resultado.getInt("estagioPesquisa"));

				int idResponsavel = resultado.getInt("idResponsavel");

				PessoaRepositorio pesquisadorRepository = new PessoaRepositorio();
				Pessoa responsavelBuscado = pesquisadorRepository.consultarPorId(idResponsavel);

				vacinaBuscada.setPesquisadorResponsavel(responsavelBuscado);

				vacinaBuscada.setPaisOrigem(resultado.getString("paisOrigem"));
				vacinaBuscada.setDataInicioPesquisa(resultado.getDate("dataInicioPesquisa"));

			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar vacina com id = " + id + " .\nCausa: " + e.getMessage());
		} finally {
			BancoDeDados.closePreparedStatement(stmt);
			BancoDeDados.closeConnection(conexao);
		}

		return vacinaBuscada;
	}

	public ArrayList<Vacina> pesquisarTodas() {

		ArrayList<Vacina> vacinas = new ArrayList();

		Connection conexao = BancoDeDados.getConnection();
		String sql = " SELECT * FROM VACINA ";

		PreparedStatement stmt = BancoDeDados.getPreparedStatement(conexao, sql);

		try {

			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				Vacina vacinaBuscada = new Vacina();
				vacinaBuscada.setId(resultado.getInt("idVacina"));
				vacinaBuscada.setEstagioPesquisa(resultado.getInt("estagioPesquisa"));

				int idResponsavel = resultado.getInt("idResponsavel");

				PessoaRepositorio pesquisadorRepository = new PessoaRepositorio();
				Pessoa responsavelBuscado = pesquisadorRepository.consultarPorId(idResponsavel);

				vacinaBuscada.setPesquisadorResponsavel(responsavelBuscado);

				vacinaBuscada.setPaisOrigem(resultado.getString("paisOrigem"));
				vacinaBuscada.setDataInicioPesquisa(resultado.getDate("dataInicioPesquisa"));

				vacinas.add(vacinaBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar vacinas \nCausa: " + e.getMessage());
		} finally {
			BancoDeDados.closePreparedStatement(stmt);
			BancoDeDados.closeConnection(conexao);
		}
		return vacinas;
	}

	// Update
	public boolean atualizar(Vacina vacina) {
		Connection conexao = BancoDeDados.getConnection();
		boolean atualizou = false;
		String sql = "update vacina set paisOrigem = ?, estagioPesquisa= ?, dataInicioPesquisa= ?, nomePesquisadorResponsavel= ? where idVacina= ?";

		PreparedStatement stmt = BancoDeDados.getPreparedStatement(conexao, sql);

		try {
			stmt.setString(1, vacina.getPaisOrigem());
			stmt.setInt(2, vacina.getEstagioPesquisa());
			stmt.setDate(3, new java.sql.Date(vacina.getDataInicioPesquisa().getTime())); // (date) ta convertendo pra
																							// data do sql
			stmt.setInt(4, vacina.getPesquisadorResponsavel().getId());

			stmt.setInt(5, vacina.getId());

			stmt.execute();

			int linhasAfetadas = stmt.executeUpdate();
			atualizou = (linhasAfetadas > 0);

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Query\ncausa:" + e.getMessage());
		} finally {
			BancoDeDados.closePreparedStatement(stmt);
			BancoDeDados.closeConnection(conexao);
		}

		return atualizou;
	}

	// Delete
	public boolean excluir(int id) {

		boolean excluiu = false;

		// Conectar no banco
		Connection conexao = BancoDeDados.getConnection();
		String sql = " DELETE FROM VACINA " + " WHERE idVacina = ? ";

		// Obter o preparedStatement
		PreparedStatement stmt = BancoDeDados.getPreparedStatement(conexao, sql);
		try {
			// Executar
			stmt.setInt(1, id);
			int registrosExcluidos = stmt.executeUpdate();
			excluiu = (registrosExcluidos > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir vacina.\nCausa: " + e.getCause());
		} finally {
			BancoDeDados.closeStatement(stmt);
			BancoDeDados.closeConnection(conexao);
		}

		return excluiu;
	}
}