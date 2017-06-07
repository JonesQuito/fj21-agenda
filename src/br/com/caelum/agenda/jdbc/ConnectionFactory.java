package br.com.caelum.agenda.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver"); // essa linha pode resolver o problema
			
			return DriverManager.getConnection("jdbc:mysql://localhost/fj21?autoReconnect=true&useSSL=false", "root", "123456");
					
			// O comando "?autoReconnect=true&useSSL=false" após o nome do banco
			// elimina mensagem de warning
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
