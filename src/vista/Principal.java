package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Equipo;
import modelo.Jugador;
import modelo.ModeloSQL;

import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JButton btnEliminar;
	private JButton btnEliminarTodo;
	private Controlador controlador;
	private ModeloSQL modeloSQL;
	private int iddel;
	private String resp;
	private Equipo equipo;
	ArrayList<Jugador> jugadoresfin = new ArrayList<Jugador>();

	private JScrollPane scrollPane;

	private Jugador jugador;
	private static   JTable table;
	
	DefaultTableModel modelotab = new DefaultTableModel();	

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public ModeloSQL getModelo() {
		return modeloSQL;
	}

	public void setModelo(ModeloSQL modeloSQL) {
		this.modeloSQL = modeloSQL;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}



	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Principal(Controlador controlador) {

		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] columnas = { "id", "Nombre", "Apellidos", "Posicion", "Equipo" };
		modelotab.setColumnIdentifiers(columnas);

		JButton btnAdd = new JButton("A\u00F1adir");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.anadirJugador();

			}
		});
		btnAdd.setBounds(659, 92, 101, 23);
		contentPane.add(btnAdd);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iddel= (int) table.getValueAt(table.getSelectedRow(),0);
				controlador.delJug(iddel);
			}
		});
		btnEliminar.setBounds(659, 126, 101, 23);
		contentPane.add(btnEliminar);

		btnEliminarTodo = new JButton("Eliminar todo");
		btnEliminarTodo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.delTodo();
			}
		});
		btnEliminarTodo.setBounds(659, 157, 101, 23);
		contentPane.add(btnEliminarTodo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setBounds(35, 95, 603, 272);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("SQL");
		comboBox.addItem("Fichero");
		comboBox.addItem("Hibernate");
		comboBox.setBounds(10, 411, 73, 20);
		contentPane.add(comboBox);
		
		JButton btnVolcar = new JButton("Volcar");
		btnVolcar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.volcar(comboBox.getSelectedItem().toString());
			}
		});
		btnVolcar.setBounds(93, 410, 101, 23);
		contentPane.add(btnVolcar);
		
		JLabel lblJugadores = new JLabel("JUGADORES");
		lblJugadores.setFont(new Font("Times New Roman", Font.PLAIN, 34));
		lblJugadores.setBounds(245, 28, 225, 56);
		contentPane.add(lblJugadores);
		
		JButton btnUpdate = new JButton("Reload");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTabla();
			}
		});
		btnUpdate.setBounds(683, 410, 101, 23);
		contentPane.add(btnUpdate);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iddel= (int) table.getValueAt(table.getSelectedRow(),0);
				controlador.actualizar(iddel,jugadoresfin);
			}
		});
		btnActualizar.setBounds(659, 191, 101, 23);
		contentPane.add(btnActualizar);
		
		

	}

	public void cargarDatos() {
		controlador.cargarDatos(resp);
		
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public void pintarTabla(Jugador jugador) {
		
		jugadoresfin.add(jugador);
		Jugador prueba = jugador;
		
		System.out.println("PINTAR TABLA " + prueba.getNombre());
		System.out.println("PINTAR TABLA " + prueba.getEquipo().getNombre());
		
		
		modelotab.addRow(new Object[] { jugador.getID(), jugador.getNombre(), jugador.getApellido(),
				jugador.getPosicion(),jugador.getEquipo().getNombre()});
		
		
		
		table.setModel(modelotab);
		
		

	}
	public void refreshTabla() {
		int rowCount = modelotab.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    modelotab.removeRow(i);
		}
		controlador.cargarDatos(resp);
	}
	public void seleccion() {
		String[] opciones = {
	            "SQL",
	            "Fichero",
	            "Hibernate",
	            "JSON-PHP",
	            "Mongo"
	        };
	         resp = (String) JOptionPane.showInputDialog(null, "Seleccione la opción requerida", "Opciones", JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);
	        if(resp.equals("SQL")) {
	        	System.out.println("lol");
	        }
		
	}
}
