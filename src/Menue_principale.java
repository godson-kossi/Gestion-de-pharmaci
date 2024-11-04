import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menue_principale {

	private JFrame frame;
	private JTextField txtnom;
	private JTextField txtprix;
	private JTextField txtreste;
	private JTextField txtpayer;
	private JTextField txttotal;
	private JTable txttable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menue_principale window = new Menue_principale();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public Menue_principale() {
		initialize();
		Table();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma", "root", "");
			 System.out.println("connection etablie");
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	}
	
	private void Table() {
		try {
			connect();
			String [] entet = {"Code", "Nom", "Prix", "Total", "Payer", "Reste"};
			String [] ligne = new String[8];
			
			DefaultTableModel model = new DefaultTableModel(null,entet);
	
			String sql="select * from tb_pharma";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				ligne[0] = rs.getString("code");
				ligne[1] = rs.getString("nom");
				ligne[2] = rs.getString("prix");
				ligne[3] = rs.getString("qte");
				ligne[4] = rs.getString("total");
				ligne[5] = rs.getString("payer");
				ligne[6] = rs.getString("reste");
				model.addRow(ligne);

			}
			txttable.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
	     }
    }
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 610, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 11, 578, 70);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTION DE PHARMACIE");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(10, 11, 558, 52);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(10, 92, 578, 228);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nom:");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 13, 73, 23);
		lblNewLabel_1.setEnabled(false);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Prix:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(10, 62, 73, 23);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Quantite:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(10, 114, 87, 23);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Total:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(320, 13, 73, 23);
		panel_1.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Payer:");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_4.setBounds(320, 62, 73, 23);
		panel_1.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Reste:");
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_5.setBounds(320, 114, 73, 23);
		panel_1.add(lblNewLabel_2_5);
		
		txtnom = new JTextField();
		txtnom.setColumns(10);
		txtnom.setBounds(107, 11, 171, 31);
		panel_1.add(txtnom);
		
		txtprix = new JTextField();
		txtprix.setHorizontalAlignment(SwingConstants.CENTER);
		txtprix.setColumns(10);
		txtprix.setBounds(107, 62, 171, 31);
		panel_1.add(txtprix);
		
		txtreste = new JTextField();
		txtreste.setHorizontalAlignment(SwingConstants.CENTER);
		txtreste.setBackground(new Color(192, 192, 192));
		txtreste.setColumns(10);
		txtreste.setBounds(397, 110, 171, 31);
		panel_1.add(txtreste);
		
		txtpayer = new JTextField();
		txtpayer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int total = Integer.parseInt(txttotal.getText());
				int payer = Integer.parseInt(txtpayer.getText());
			int reste = payer - total;
			txtreste.setText(String.valueOf(reste));
			}
			
		});
		txtpayer.setHorizontalAlignment(SwingConstants.CENTER);
		txtpayer.setBackground(new Color(128, 255, 0));
		txtpayer.setColumns(10);
		txtpayer.setBounds(397, 58, 171, 31);
		panel_1.add(txtpayer);
		
		txttotal = new JTextField();
		txttotal.setHorizontalAlignment(SwingConstants.CENTER);
		txttotal.setBackground(new Color(128, 128, 128));
		txttotal.setColumns(10);
		txttotal.setBounds(397, 14, 171, 31);
		panel_1.add(txttotal); 
		
		JSpinner txtquantite = new JSpinner();
		txtquantite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int prix = Integer.parseInt(txtprix.getText());
				int qte = Integer.parseInt(txtquantite.getValue().toString());
				
				int total = prix*qte;
				txttotal.setText(String.valueOf(total));
				
			}
		});
		txtquantite.setBounds(107, 112, 171, 31);
		panel_1.add(txtquantite);
		
		JButton btnNewButton = new JButton("Enregistrer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connect();
					pst = con.prepareStatement("insert into tb_pharma(nom, prix, qte,total,reste,payer) values(?,?,?,?,?,?)");
					pst.setString(1, txtnom.getText());
					pst.setString(2, txtprix.getText());
					pst.setString(3, txtquantite.getValue().toString());
					pst.setString(5, txttotal.getText());
					pst.setString(4, txtreste.getText());
					pst.setString(6, txtpayer.getText());
					pst.executeUpdate();
					con.close();
					JOptionPane.showMessageDialog(null, txtnom.getText()+" Ajouter");
					
				}catch  (Exception e2) {
					e2.printStackTrace();
			    }
			}
	    });
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(397, 163, 171, 37);
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(10, 326, 578, 108);
		frame.getContentPane().add(scrollPane);
		
		txttable = new JTable();
		scrollPane.setViewportView(txttable);
	}
}

