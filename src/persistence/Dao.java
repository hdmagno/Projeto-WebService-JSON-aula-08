package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	String url = "jdbc:mysql://localhost:3306/bd_aula08";
	String usuario = "root";
	String senha = "1234";

	public void conectar() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, usuario, senha);
	}
	
	public void desconectar() throws Exception {
		con.close();
	}
	
}
