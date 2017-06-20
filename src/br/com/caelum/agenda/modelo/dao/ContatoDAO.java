package br.com.caelum.agenda.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.agenda.jdbc.ConnectionFactory;
import br.com.caelum.agenda.modelo.Contato;

public class ContatoDAO {

	// a conexão com o banco de dados
	private Connection connection;

	public ContatoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = "insert into contatos" + "(nome, email, endereco, dataNascimento)" + " values (?,?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

			// executa
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Contato pesquisarContato(Long idContato) {

		String sql = "select * from contatos where id = " + idContato;

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			// stmt.setLong(1, idContato);
			ResultSet rs = stmt.executeQuery();
			Contato contato = new Contato();

			while (rs.next()) {
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
			}

			rs.close();
			stmt.close();
			this.connection.close();
			return contato;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Contato> getLista() {

		try {
			// criar uma lista para ser preenchida com os contatos lidos no
			// banco
			List<Contato> contatos = new ArrayList<Contato>();

			// criar um preparedStatement para executar o script SQL de consulta
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos");

			// criar um resultSet que armazena o resultado da consulta
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criar um objeto contato e setar seus atributos
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// adicionando o contato à lista
				contatos.add(contato);
			}

			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Contato contato) {

		// String sql = "UPDATE contatos SET nome=?, email=?, endereco=?,
		// dataNascimento=? WHERE id=?";
		String sql = "UPDATE contatos SET nome=?, email=?, endereco=?, dataNascimento=? WHERE id=?";

		// PreparedStatement stmt;

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);

			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(Contato contato) {

		try {
			PreparedStatement stmt = connection.prepareStatement("delete from contatos where id=?");
			stmt.setLong(1, contato.getId());

			stmt.execute();

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
