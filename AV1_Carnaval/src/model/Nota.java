package model;

public class Nota 
{
	private int id_escola;
	private int id_quesito;
	private int id_jurado;
	private float nota;
	private float nota_menor;
	private float nota_maior;
	private float nota_total;
	private float nota_total_quesitos;
	
	public int getId_escola() 
	{
		return id_escola;
	}
	public void setId_escola(int id_escola) 
	{
		this.id_escola = id_escola;
	}
	public int getId_quesito() 
	{
		return id_quesito;
	}
	public void setId_quesito(int id_quesito) 
	{
		this.id_quesito = id_quesito;
	}
	public int getId_jurado() 
	{
		return id_jurado;
	}
	public void setId_jurado(int id_jurado) 
	{
		this.id_jurado = id_jurado;
	}
	public float getNota()
	{
		return nota;
	}
	public void setNota(float nota)
	{
		this.nota = nota;
	}
	public float getNota_menor()
	{
		return nota_menor;
	}
	public void setNota_menor(float nota_menor)
	{
		this.nota_menor = nota_menor;
	}
	public float getNota_maior()
	{
		return nota_maior;
	}
	public void setNota_maior(float nota_maior)
	{
		this.nota_maior = nota_maior;
	}
	public float getNota_total()
	{
		return nota_total;
	}
	public void setNota_total(float nota_total)
	{
		this.nota_total = nota_total;
	}
	public float getNota_total_quesitos() 
	{
		return nota_total_quesitos;
	}
	public void setNota_total_quesitos(float nota_total_quesitos)
	{
		this.nota_total_quesitos = nota_total_quesitos;
	}
}
