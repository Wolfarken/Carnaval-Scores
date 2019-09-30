package view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.TelaController;
import controller.VerTotalController;
import model.Escola;
import model.Jurado;
import model.Quesito;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class EscolaNota extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	
	private JPanel panel;
	private JComboBox<Escola> comboEscola;
	private JComboBox<Jurado> comboJurado;
	private JComboBox<Quesito> comboQuesito;
	private JTextField textNota;
	private JLabel labelEscola;
	private JLabel labelJurado;
	private JLabel labelQuesito;
	private JLabel labelNota;
	private JButton buttonVerTotal;
	private JButton buttonVerQuesito;
	private JButton buttonInserir;
	private JTable tableVerTotal;
	private DefaultTableModel defaultTableModelo;
	private JScrollPane scrollVerTotal;
	

	public static void main(String[] args) 
	{
		EventQueue.invokeLater
		(new Runnable() 
			{
				public void run() 
				{
					try 
					{
						EscolaNota window = new EscolaNota();
						window.setTitle("LAB BD - AV1 Carnaval");
						window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						window.setBounds(200, 150, 1000, 350);
						window.setVisible(true);
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		);
	}

	public EscolaNota() 
	{
		//Panel setup
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		panel.setLayout(null);
		
		//labelEscola setup
		labelEscola = new JLabel("Escola");
		labelEscola.setBounds(30, 50, 80, 20);
		panel.add(labelEscola);
		
		//comboEscola setup
		comboEscola = new JComboBox<Escola>();
		comboEscola.setBounds(100, 50, 350, 25);
		comboEscola.setEnabled(false);
		panel.add(comboEscola);
		
		//labelJurado setup
		labelJurado = new JLabel("Jurado");
		labelJurado.setBounds(30, 100, 80, 20);
		panel.add(labelJurado);
		
		//comboJurado setup
		comboJurado = new JComboBox<Jurado>();
		comboJurado.setBounds(100, 100, 350, 25);
		comboJurado.setEnabled(false);
		panel.add(comboJurado);
		
		//labelQuesito setup
		labelQuesito = new JLabel("Quesito");
		labelQuesito.setBounds(30, 150, 80, 20);
		panel.add(labelQuesito);
		
		//comboQuesito setup
		comboQuesito = new JComboBox<Quesito>();
		comboQuesito.setBounds(100, 150, 350, 25);
		comboQuesito.setEnabled(false);
		panel.add(comboQuesito);
		
		//labelNota setup
		labelNota = new JLabel("Nota");
		labelNota.setBounds(30, 200, 80, 20);
		panel.add(labelNota);
		
		//textNota setup
		textNota = new JTextField();
		textNota.setBounds(100, 200, 50, 25);
		panel.add(textNota);
		textNota.setColumns(3);
		
		//scrollVerTotal setup
		scrollVerTotal = new JScrollPane();
		scrollVerTotal.setBounds(470, 30, 500, 250);
		panel.add(scrollVerTotal);
		
		//tableVerQuesito setup
		tableVerTotal = new JTable();
		scrollVerTotal.setViewportView(tableVerTotal);
		defaultTableModelo = new DefaultTableModel (new Object[][] {},
													new String[] {"Escola", "Nota Total"});
		tableVerTotal.setModel(defaultTableModelo);
		tableVerTotal.getColumnModel().getColumn(0).setPreferredWidth(430);
		tableVerTotal.getColumnModel().getColumn(1).setPreferredWidth(70);
		
		//buttonInserir setup
		buttonInserir = new JButton("Inserir");
		buttonInserir.setBounds(150, 200, 100, 25);
		panel.add(buttonInserir);
		//buttonInserir ActionListener
		ActionListener inserir = new TelaController(comboEscola, comboQuesito, comboJurado, textNota);
		buttonInserir.addActionListener(inserir);
		textNota.addActionListener(inserir);
		
		//buttonVerQuesito setup
		buttonVerQuesito = new JButton("Ver Quesito");
		buttonVerQuesito.setBounds(200, 250, 150, 25);
		panel.add(buttonVerQuesito);
		//buttonVerQuesito ActionListener
		ActionListener verQuesito = new VerQuesito();
		buttonVerQuesito.addActionListener(verQuesito);
		
		//buttonVerTotal setup
		buttonVerTotal = new JButton("Ver Total");
		buttonVerTotal.setBounds(350, 250, 100, 25);
		panel.add(buttonVerTotal);
		//buttonVerTotal ActionListener
		ActionListener verTotal = new VerTotalController(tableVerTotal);
		buttonVerTotal.addActionListener(verTotal);
		
		
		
		TelaController telaController = new TelaController(comboEscola, comboQuesito, comboJurado, textNota);
		telaController.PopularComboBoxEscola();
		telaController.PopularComboBoxQuesito();
		telaController.PopularComboBoxJurado();
		
		VerTotalController verTotalController = new VerTotalController(tableVerTotal);
		verTotalController.TabelaVerTotal();
	}
}
