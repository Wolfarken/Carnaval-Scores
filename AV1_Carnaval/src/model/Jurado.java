package model;

public class Jurado 
{
	private int id_jurado;
	private String nome_jurado;
	
	public int getId_jurado() 
	{
		return id_jurado;
	}
	public void setId_jurado(int id_jurado) 
	{
		this.id_jurado = id_jurado;
	}
	public String getNome_jurado() 
	{
		return nome_jurado;
	}
	public void setNome_jurado(String nome_jurado) 
	{
		this.nome_jurado = nome_jurado;
	}
	
	@Override
	public String toString() 
	{
		return this.nome_jurado;
	}
}
