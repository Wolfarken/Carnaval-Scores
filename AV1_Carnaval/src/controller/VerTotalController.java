package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Nota;
import persistence.NotaQuesitoDAO;

public class VerTotalController implements ActionListener 
{
	private JTable tableVerTotal;
	
	public VerTotalController(JTable tableVerTotal)
	{
		this.tableVerTotal = tableVerTotal;
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		TabelaVerTotal();
	}
	
	public void TabelaVerTotal()
	{
		if (tableVerTotal != null)
		{
			DefaultTableModel defaultTableModelo = (DefaultTableModel) tableVerTotal.getModel();
			
			try 
			{
				NotaQuesitoDAO notaQuesitoDAO = new NotaQuesitoDAO();
				List<Nota> listaVerTotalNota = notaQuesitoDAO.verTotal();
				
				if (defaultTableModelo.getRowCount() > 0)
				{
					defaultTableModelo.setRowCount(0);
				}
				
				for (Nota nota : listaVerTotalNota)
				{
					Object[] linha = new Object[2];
					linha[0] = nota.getId_escola();
					linha[1] = nota.getNota_total_quesitos();
					
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
