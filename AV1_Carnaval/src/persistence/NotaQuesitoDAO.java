package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Escola;
import model.Jurado;
import model.Nota;
import model.Quesito;

public class NotaQuesitoDAO 
{
	public Connection connection;
	
	public NotaQuesitoDAO() throws ClassNotFoundException, SQLException
	{
		GenericDAO gDAO = new GenericDAO();
		connection = gDAO.getConnection();
	}
	
	
	public String inserirNota(Escola escola, Quesito quesito, Jurado jurado, Nota nota) throws SQLException
	{
		String saida = "";
		String sql	 = "{CALL SP_Nota(?,?,?,?,?)}";
		CallableStatement cs = connection.prepareCall(sql);
		
		//Parametro da procedure
		cs.setInt(1, escola.getId_escola());
		cs.setInt(2, quesito.getId_quesito());
		cs.setInt(3, jurado.getId_jurado());
		cs.setFloat(4, nota.getNota());
		
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		
		//Saida do procedure
		saida = cs.getString(5);
		return saida;
	}
	
	public List<Nota> verQuesito(int quesitoIndex) throws SQLException
	{
		List<Nota> listaVerQuesitoNota = new ArrayList<Nota>();
		
		String sql = "SELECT id_escola, id_jurado, nota, nota_menor, nota_maior, nota_total FROM Nota WHERE id_quesito = " + quesitoIndex;
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		{
			Nota nota = new Nota();
			nota.setId_escola(rs.getInt("id_escola"));
			nota.setId_jurado(rs.getInt("id_jurado"));
			nota.setNota(rs.getFloat("nota"));
			nota.setNota_menor(rs.getFloat("nota_menor"));
			nota.setNota_maior(rs.getFloat("nota_maior"));
			nota.setNota_total(rs.getFloat("nota_total"));
			
			listaVerQuesitoNota.add(nota);
		}
		
		rs.close();
		ps.close();
		
		return listaVerQuesitoNota;
	}
	
	public List<Nota> verTotal() throws SQLException
	{
		List<Nota> listaVerTotalNota = new ArrayList<Nota>();
		
		String sql = "{CALL SP_Nota_Total}";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		{
			Nota nota = new Nota();
			nota.setId_escola(rs.getInt("id_escola"));
			nota.setNota_total_quesitos(rs.getFloat("nota_total_quesitos"));
			
			listaVerTotalNota.add(nota);
		}
		
		rs.close();
		ps.close();
		
		return listaVerTotalNota;
	}
	
	public List<Escola> PopularEscola() throws SQLException
	{
		List<Escola> listaEscola = new ArrayList<Escola>();
		
		String sql = "SELECT id_escola, nome_escola FROM Escola";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		{
			Escola escola = new Escola();
			escola.setId_escola(rs.getInt("id_escola"));
			escola.setNome_escola(rs.getString("nome_escola"));
			
			listaEscola.add(escola);
		}
		
		rs.close();
		ps.close();
		
		return listaEscola;
	}
	
	public List<Jurado> PopularJurado() throws SQLException
	{
		List<Jurado> listaJurado = new ArrayList<Jurado>();
		
		String sql = "SELECT id_jurado, nome_jurado FROM Jurado";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		{
			Jurado jurado = new Jurado();
			jurado.setId_jurado(rs.getInt("id_jurado"));
			jurado.setNome_jurado(rs.getString("nome_jurado"));
			
			listaJurado.add(jurado);
		}
		
		rs.close();
		ps.close();
		
		return listaJurado;
	}
	
	public List<Quesito> PopularQuesito() throws SQLException
	{
		List<Quesito> listaQuesito = new ArrayList<Quesito>();
		
		String sql = "SELECT id_quesito, nome_quesito FROM Quesito";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		{
			Quesito quesito = new Quesito();
			quesito.setId_quesito(rs.getInt("id_quesito"));
			quesito.setNome_quesito(rs.getString("nome_quesito"));
			
			listaQuesito.add(quesito);
		}
		
		rs.close();
		ps.close();
		
		return listaQuesito;
	}
}
