package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Escola;
import model.Jurado;
import model.Nota;
import model.Quesito;
import persistence.NotaQuesitoDAO;

public class TelaController implements ActionListener
{
	private JComboBox<Escola> comboEscola;
	private JComboBox<Jurado> comboJurado;
	private JComboBox<Quesito> comboQuesito;
	private JTextField textNota;
	
	private boolean notaComSucesso = false;
	
	
	public TelaController(JComboBox<Escola> comboEscola, JComboBox<Quesito> comboQuesito, JComboBox<Jurado> comboJurado, JTextField textNota) 
	{
		this.comboEscola = comboEscola;
		this.comboJurado = comboJurado;
		this.comboQuesito = comboQuesito;
		this.textNota = textNota;
	}
	
	public TelaController(JComboBox<Quesito> comboQuesito) 
	{
		this.comboQuesito = comboQuesito;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		SalvarNota();
		
		if (notaComSucesso)
		{
			ComboController();
			notaComSucesso = false;
		}
	}
	

	private void SalvarNota() 
	{
		Escola escola = new Escola();
		escola.setId_escola((int) comboEscola.getSelectedIndex());

		Quesito quesito = new Quesito();
		quesito.setId_quesito((int) comboQuesito.getSelectedIndex());
		
		Jurado jurado = new Jurado();
		jurado.setId_jurado((int) comboJurado.getSelectedIndex());
		
		Nota nota = new Nota();
		float f = Float.parseFloat(textNota.getText());
		nota.setNota(f);
		

		
		String saida = "";
		
		try 
		{
			NotaQuesitoDAO notaDAO = new NotaQuesitoDAO();
			saida = notaDAO.inserirNota(escola, quesito, jurado, nota);
			
			notaComSucesso = true;
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			saida = e.getMessage();
			e.printStackTrace();
		} 
		finally 
		{
			System.out.println(saida);
			JOptionPane.showMessageDialog(null, saida);
			textNota.setText("");
		}
	}
	
	
	
	private void ComboController() 
	{
		int selectedIndexEscola = comboEscola.getSelectedIndex();
		int selectedIndexJurado = comboJurado.getSelectedIndex();
		int selectedIndexQuesito = comboQuesito.getSelectedIndex();
		
		if (selectedIndexEscola < 13)
		{
			selectedIndexEscola++;
			
			comboEscola.setSelectedIndex(selectedIndexEscola);
		}
		else if (selectedIndexJurado < 4)
		{
			selectedIndexEscola = 0;
			selectedIndexJurado++;
			
			comboEscola.setSelectedIndex(selectedIndexEscola);
			comboJurado.setSelectedIndex(selectedIndexJurado);
		}
		else if (selectedIndexQuesito < 8)
		{
			selectedIndexEscola = 0;
			selectedIndexJurado = 0;
			selectedIndexQuesito++;

			comboEscola.setSelectedIndex(selectedIndexEscola);
			comboJurado.setSelectedIndex(selectedIndexJurado);
			comboQuesito.setSelectedIndex(selectedIndexQuesito);
		}
	}
	
	
	
	public void PopularComboBoxEscola()
	{
		try 
		{
			NotaQuesitoDAO notaQuesitoDAO = new NotaQuesitoDAO();
			List<Escola> listaEscola = notaQuesitoDAO.PopularEscola();
			
			if (listaEscola != null)
			{
				for (Escola escola : listaEscola)
				{
					comboEscola.addItem(escola);
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void PopularComboBoxQuesito()
	{
		try 
		{
			NotaQuesitoDAO notaQuesitoDAO = new NotaQuesitoDAO();
			List<Quesito> listaQuesito = notaQuesitoDAO.PopularQuesito();
			
			if (listaQuesito != null)
			{
				for (Quesito quesito : listaQuesito)
				{
					comboQuesito.addItem(quesito);
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void PopularComboBoxJurado()
	{
		try 
		{
			NotaQuesitoDAO notaQuesitoDAO = new NotaQuesitoDAO();
			List<Jurado> listaJurado = notaQuesitoDAO.PopularJurado();
			
			if (listaJurado != null)
			{
				for (Jurado jurado : listaJurado)
				{
					comboJurado.addItem(jurado);
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}