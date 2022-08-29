package model.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BancoDeDados;
import model.entidade.Pessoa;

public class PessoaRepositorio {

	// CRUD

	// Create
	public Pessoa inserir(Pessoa novaPessoa) {
		// Conectar no banco --> igual para todos
		Connection conexao = BancoDeDados.getConnection();
		String sql = " insert into pesquisador (nome, cpf, matricula, dataNascimento) value (?, ?, ?, ?) ";

		// Obter o PreparedStatement
		PreparedStatement stmt = BancoDeDados.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setString(1, novaPessoa.getNome());
			stmt.setString(2, novaPessoa.getCpf());
			stmt.setInt(3, novaPessoa.getMatricula());
			stmt.setDate(4, new Date(novaPessoa.getDataNascimento().getTime())); // (date) ta convertendo pra data
																					// do sql

			stmt.execute();

			// Obter a chave gerada
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int idGerado = generatedKeys.getInt(1);
				// Preencher a chave em novaVacina
				novaPessoa.setId(idGerado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar Query\ncausa:" + e.getMessage());
		} finally {
			BancoDeDados.closePreparedStatement(stmt);
			BancoDeDados.closeConnection(conexao);
		}
		return novaPessoa;
	}

	// Retrieve

	public Pessoa consultarPorId(int id) {

		Pessoa pesquisadorBuscado = null;
		Connection conexao = BancoDeDados.getConnection();
		String sql = " SELECT * FROM PESQUISADOR WHERE idPesquisador = ? ";
		PreparedStatement stmt = BancoDeDados.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				pesquisadorBuscado = new Pessoa();
				pesquisadorBuscado.setId(resultado.getInt("id"));
				pesquisadorBuscado.setNome(resultado.getString("nome"));
				pesquisadorBuscado.setCpf(resultado.getString("cpf"));
				pesquisadorBuscado.setMatricula(resultado.getInt("matricula"));
				pesquisadorBuscado.setDataNascimento(resultado.getDate("dataNascimento"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pesquisador com id = " + id + " .\nCausa: " + e.getMessage());
		} finally {
			BancoDeDados.closePreparedStatement(stmt);
			BancoDeDados.closeConnection(conexao);
		}
		return pesquisadorBuscado;
	}

	// Update
	public boolean atualizar(Pessoa pessoa) {
		Connection conexao = BancoDeDados.getConnection();
		boolean atualizou = false;

		String sql = "update pesquisador set nome = ?, cpf= ?, matricula= ?, dataNascimento= ? where id= ?";

		PreparedStatement stmt = BancoDeDados.getPreparedStatement(conexao, sql);

		try {
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getCpf());
			stmt.setInt(3, pessoa.getMatricula());
			stmt.setDate(4, new java.sql.Date(pessoa.getDataNascimento().getTime())); // (date) ta convertendo pra
																						// data do sql
			stmt.setInt(5, pessoa.getId());
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
		String sql = " DELETE FROM PESQUISADOR " + " WHERE idPesquisador = ? ";

		// Obter o preparedStatement
		PreparedStatement stmt = BancoDeDados.getPreparedStatement(conexao, sql);
		try {
			// Executar
			stmt.setInt(1, id);
			int registrosExcluidos = stmt.executeUpdate();
			excluiu = (registrosExcluidos > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pesquisador.\nCausa: " + e.getMessage());
		} finally {
			BancoDeDados.closeStatement(stmt);
			BancoDeDados.closeConnection(conexao);
		}

		return excluiu;
	}
}
