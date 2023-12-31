package com.mx.alura.hotel.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mx.alura.hotel.dao.HuespedesDAO;
import com.mx.alura.hotel.dao.ReservasDAO;
import com.mx.alura.hotel.modelo.Huespedes;
import com.mx.alura.hotel.modelo.Reservas;
import com.mx.alura.hotel.utils.JPAUtils;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
   	
	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("/home/aldo/Documentos/Proyectos/Proyecto-Hotel-Alura/src/imagenes/lupa2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon("/home/aldo/Documentos/Proyectos/Proyecto-Hotel-Alura/src/imagenes/reservado.png"), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon("/home/aldo/Documentos/Proyectos/Proyecto-Hotel-Alura/src/imagenes/pessoas.png"), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		buscar();
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("/home/aldo/Documentos/Proyectos/Proyecto-Hotel-Alura/src/imagenes/Ha-100px.png"));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		final JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		final JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!txtBuscar.getText().isEmpty()) {
					
					try {
						EntityManager em = JPAUtils.getEntityManager();
						
						 ReservasDAO reservasDao = new ReservasDAO(em);
						
					    Long numero = Long.parseLong(txtBuscar.getText());
					    
					   Reservas reserva = reservasDao.consultaPorId(numero);
					   
					   
					   if(reserva != null) {
						  
						     em.getTransaction().begin();
						   
						      modelo.setRowCount(0);
						   
						    modelo.addRow(new Object[]{
						        reserva.getId(),                    
						        reserva.getFechaDeEntrada(),       
						        reserva.getFechaDeSalida(),        
						        reserva.getValor(),                 
						        reserva.getFormaDePago()          
						    });
						    
						  
						   em.close();
						   
					   } else {
						   JOptionPane.showMessageDialog(null, "No existen registros con ese numero");
					   }

					} catch (NumberFormatException ex) {
					    
						EntityManager em = JPAUtils.getEntityManager();
						
						HuespedesDAO huespedesDao = new HuespedesDAO(em); 
						
						List<Huespedes> huespedes = huespedesDao.consultarPorApellido(txtBuscar.getText());
						
						if(!huespedes.isEmpty()) {
							
							em.getTransaction().begin();
							
							modeloHuesped.setRowCount(0);
							
							for (Huespedes huesped : huespedes) {
							    modeloHuesped.addRow(new Object[] {
							    	huesped.getId(),	
							        huesped.getNombre(),                    
							        huesped.getApellido(),       
							        huesped.getFechaDeNacimiento(),        
							        huesped.getNacionalidad(),                 
							        huesped.getTelefono(),
							        huesped.getReserva().getId()
							    });
							}
							
							em.close();
							
						} else {
							JOptionPane.showMessageDialog(null, "No existen registros con ese apellido en los huespedes");
						}
					}
					
				}   else {
					JOptionPane.showMessageDialog(null, "Debes llenar la casilla de biusqueda.");
				}
				
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int filaModelo = tbReservas.getSelectedRow();
				
				int filaModeloHuesped = tbHuespedes.getSelectedRow();
				
				if(filaModelo >= 0 || filaModeloHuesped >= 0) {
					
					if(filaModelo >= 0 && filaModeloHuesped < 0) {
						  
						EntityManager em = JPAUtils.getEntityManager();
						
						Long id = Long.parseLong(tbReservas.getValueAt(filaModelo, 0).toString());
						Date fechaEntrada = (Date) (tbReservas.getValueAt(filaModelo, 1)); 
						Date fechaSalida = (Date) (tbReservas.getValueAt(filaModelo, 2));
						String valorStr = tbReservas.getValueAt(filaModelo, 3).toString();
						BigDecimal valor = new BigDecimal(valorStr);
						String formaPago = tbReservas.getValueAt(filaModelo, 4).toString();
						
					    ReservasDAO reservasDao = new ReservasDAO(em);
						
						Reservas reserva = reservasDao.consultaPorId(id);
						    
						em.getTransaction().begin();
						
						        reserva.setFechaDeEntrada(fechaEntrada);       
						        reserva.setFechaDeSalida(fechaSalida);        
						        reserva.setValor(valor);                 
						        reserva.setFormaDePago(formaPago);
						        
					   reservasDao.actualizar(reserva);   
					   
					   em.getTransaction().commit();
					   
					   em.close();
					   
					   JOptionPane.showMessageDialog(null, "Datos Actualizados");
					  
					   modelo.setRowCount(0);
					   modeloHuesped.setRowCount(0);
					   
					   buscar();
						
					} else {
						
						EntityManager em = JPAUtils.getEntityManager();
						
						
						 
					 Long id = Long.parseLong(tbHuespedes.getValueAt(filaModeloHuesped, 0).toString());	
					 String nombre = tbHuespedes.getValueAt(filaModeloHuesped, 1).toString();
					 String apellido = tbHuespedes.getValueAt(filaModeloHuesped, 2).toString();
					 Date fechaNacimiento = (Date) (tbHuespedes.getValueAt(filaModeloHuesped, 3));
					 String nacionalidad = tbHuespedes.getValueAt(filaModeloHuesped, 4).toString();
					 String telefono = tbHuespedes.getValueAt(filaModeloHuesped, 5).toString();
					 
					 HuespedesDAO huespedesDao = new HuespedesDAO(em);
					 
					 Huespedes huesped = huespedesDao.consultaPorId(id);
					 
					 em.getTransaction().begin();
					 
					    	
						        huesped.setNombre(nombre);                    
						        huesped.setApellido(apellido);       
						        huesped.setFechaDeNacimiento(fechaNacimiento);        
						        huesped.setNacionalidad(nacionalidad);                 
						        huesped.setTelefono(telefono);
						        
					huespedesDao.actualizar(huesped);
					
					em.getTransaction().commit();
					
					em.close();
					
					JOptionPane.showMessageDialog(null, "Datos Actualizados");
					
					modeloHuesped.setRowCount(0);
					modelo.setRowCount(0);
					
					buscar();
						        
					}
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Seleciona la fila que deseas editar.");
				}
				
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
                 int filaModelo = tbReservas.getSelectedRow();
				
				int filaModeloHuesped = tbHuespedes.getSelectedRow();
				
				if(filaModelo >= 0 || filaModeloHuesped >= 0) {
					
					if(filaModelo >= 0 && filaModeloHuesped < 0) {
						   
						EntityManager em = JPAUtils.getEntityManager();
						
						Long id = Long.parseLong(tbReservas.getValueAt(filaModelo, 0).toString());
						
						 ReservasDAO reservasDao = new ReservasDAO(em);
							
							Reservas reserva = reservasDao.consultaPorId(id);
						
							HuespedesDAO huespedesDao = new HuespedesDAO(em);
							
							Huespedes huesped = huespedesDao.consultarPorIdReserva(id);
							
							em.getTransaction().begin();

							huespedesDao.eliminar(huesped);
							
						   reservasDao.eliminar(reserva);   
						   
						   
						   em.getTransaction().commit();
						   
						   em.close();
						   
						   JOptionPane.showMessageDialog(null, "Datos Eliminados");
						  
						   modelo.setRowCount(0);
						   modeloHuesped.setRowCount(0);
						   
						   buscar();
						
					} else {
						
						EntityManager em = JPAUtils.getEntityManager();
						
						Long id = Long.parseLong(tbHuespedes.getValueAt(filaModeloHuesped, 0).toString());
						
						 HuespedesDAO huespedesDao = new HuespedesDAO(em);
						 
						 Huespedes huesped = huespedesDao.consultaPorId(id);
						 
						 ReservasDAO reservasDao = new ReservasDAO(em);
						 
						 Reservas reserva = reservasDao.consultaPorId(huesped.getReserva().getId());
						 
						 em.getTransaction().begin();
							        
						huespedesDao.eliminar(huesped);

						reservasDao.eliminar(reserva);
						
						em.getTransaction().commit();
						
						em.close();
						
						JOptionPane.showMessageDialog(null, "Datos Eliminados");
						
						modeloHuesped.setRowCount(0);
						modelo.setRowCount(0);
						
						buscar();
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Seleciona la fila que deseas editar.");
				}
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
	    public  void buscar() {
	    	
	    	EntityManager em = JPAUtils.getEntityManager();
	    	
	    	
	    	ReservasDAO reservasDao = new ReservasDAO(em);
			
			List<Reservas> reservas = reservasDao.consultarTodos();
			
			for (Reservas reserva : reservas) {
			   
						modelo.addRow(new Object[] {
						    reserva.getId(),                    
						    reserva.getFechaDeEntrada(),       
						    reserva.getFechaDeSalida(),        
						    reserva.getValor(),                 
						    reserva.getFormaDePago()          
						});
			    
			}
			
			HuespedesDAO huespedesDao = new HuespedesDAO(em);
			
			List<Huespedes> huespedes = huespedesDao.consultarTodos();
			
			for (Huespedes huesped : huespedes) {
			    modeloHuesped.addRow(new Object[] {
			    	huesped.getId(),	
			        huesped.getNombre(),                    
			        huesped.getApellido(),       
			        huesped.getFechaDeNacimiento(),        
			        huesped.getNacionalidad(),                 
			        huesped.getTelefono(),
			        huesped.getReserva().getId()
			    });
			}
			
		}
}
