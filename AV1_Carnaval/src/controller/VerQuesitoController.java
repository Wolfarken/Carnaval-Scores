package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Nota;
import model.Quesito;
import persistence.NotaQuesitoDAO;

public class VerQuesitoController implements ActionListener 
{
	private JComboBox<Quesito> comboQuesito;
	private JTable tableVerQuesito;
	
	public VerQuesitoController(JComboBox<Quesito> comboQuesito, JTable tableQuesitoTotal)
	{
		this.comboQuesito = comboQuesito;
		this.tableVerQuesito = tableQuesitoTotal;
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		TabelaVerQuesito();
	}
	
	public void TabelaVerQuesito()
	{
		Quesito quesito = new Quesito();
		int quesitoIndex = comboQuesito.getSelectedIndex();
		
		if (tableVerQuesito != null)
		{
			DefaultTableModel defaultTableModelo = (DefaultTableModel) tableVerQuesito.getModel();
			
			try 
			{
				NotaQuesitoDAO notaQuesitoDAO = new NotaQuesitoDAO();
				List<Nota> listaVerQuesitoNota = notaQuesitoDAO.verQuesito(quesitoIndex);
				
				if (defaultTableModelo.getRowCount() > 0)
				{
					defaultTableModelo.setRowCount(0);
				}
				
				for (Nota nota : listaVerQuesitoNota)
				{
					Object[] linha = new Object[6];
					linha[0] = nota.getId_escola();
					linha[1] = nota.getId_jurado();
					linha[2] = nota.getNota();
					linha[3] = nota.getNota_menor();
					linha[4] = nota.getNota_maior();
					linha[5] = nota.getNota_total();
					
					defaultTableModelo.addRow(linha);
				}
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
