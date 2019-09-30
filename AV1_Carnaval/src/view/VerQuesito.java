package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.TelaController;
import controller.VerQuesitoController;
import controller.VerTotalController;
import model.Quesito;

public class VerQuesito extends JFrame implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JComboBox<Quesito> comboQuesito;
	private JTable tableVerQuesito;
	private DefaultTableModel defaultTableModelo;
	private JScrollPane scrollVerQuesito;
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		EventQueue.invokeLater
		(new Runnable() 
			{
				public void run() 
				{
					try 
					{
						VerQuesito verQuesito = new VerQuesito();
						verQuesito.setTitle("LAB BD - AV1 Carnaval - Ver Quesito");
						verQuesito.setBounds(200, 500, 1000, 350);
						verQuesito.setVisible(true);
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		);
	}
	
	public VerQuesito() 
	{
		//Panel setup
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		panel.setLayout(null);
		
		//scrollVerQuesito setup
		scrollVerQuesito = new JScrollPane();
		scrollVerQuesito.setBounds(30, 60, 940, 230);
		panel.add(scrollVerQuesito);
		
		//tableVerQuesito setup
		tableVerQuesito = new JTable();
		scrollVerQuesito.setViewportView(tableVerQuesito);
		defaultTableModelo = new DefaultTableModel (new Object[][] {},
													new String[] {"Escola", "Jurado", "Nota", "Menor Descartada", "Maior Descartada", "Nota Total"});
		tableVerQuesito.setModel(defaultTableModelo);
		tableVerQuesito.getColumnModel().getColumn(0).setPreferredWidth(450);
		tableVerQuesito.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableVerQuesito.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableVerQuesito.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableVerQuesito.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableVerQuesito.getColumnModel().getColumn(5).setPreferredWidth(70);
		
		//comboQuesito setup
		comboQuesito = new JComboBox<Quesito>();
		comboQuesito.setBounds(30, 30, 350, 25);
		panel.add(comboQuesito);
		//comboQuesito ActionListener
		ActionListener verQuesito = new VerQuesitoController(comboQuesito, tableVerQuesito);
		comboQuesito.addActionListener(verQuesito);
		
		
		
		TelaController telaController = new TelaController(comboQuesito);
		telaController.PopularComboBoxQuesito();
	}
}
