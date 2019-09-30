package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO 
{
	private Connection connection;

	public Connection getConnection() throws ClassNotFoundException, SQLException 
	{
		String hostName = "localhost";
		String dbName = "Carnaval_AV1";
		String user = "player";
		String senha = "123";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		connection = DriverManager.getConnection(String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", hostName, dbName, user, senha));
		
		System.out.println("Login efetuado");

		return connection;
	}
}