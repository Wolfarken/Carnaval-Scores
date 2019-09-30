package model;

public class Escola 
{
	private int id_escola;
	private String nome_escola;
	
	public int getId_escola() 
	{
		return id_escola;
	}
	public void setId_escola(int id_escola) 
	{
		this.id_escola = id_escola;
	}
	public String getNome_escola() 
	{
		return nome_escola;
	}
	public void setNome_escola(String nome_escola) 
	{
		this.nome_escola = nome_escola;
	}
	
	@Override
	public String toString() 
	{
		return this.nome_escola;
	}
}
