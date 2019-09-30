package model;

public class Quesito 
{
	private int id_quesito;
	private String nome_quesito;
	
	public int getId_quesito() 
	{
		return id_quesito;
	}
	public void setId_quesito(int id_quesito) 
	{
		this.id_quesito = id_quesito;
	}
	public String getNome_quesito() {
		return nome_quesito;
	}
	public void setNome_quesito(String nome_quesito) {
		this.nome_quesito = nome_quesito;
	}
	
	@Override
	public String toString() 
	{
		return this.nome_quesito;
	}
}
