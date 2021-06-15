package project.uas.analog;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;

public class DataMhs {

	private JFrame frame;
	private JTextField jtxtNIM;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	private JTextField jtxtNama;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField jtxtAlamat;
	private JTextField jtxtJenisKelamin;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField jtxtProdiJurusan;
	private JTextField jtxtIPK;
	private JButton btnPrint;
	private JButton btnReset;
	private JButton btnKeluar;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rst = null;
	
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataMhs window = new DataMhs();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DataMhs() {
		initialize();
		
		conn = DataMahasiswa.ConnectDB();
		Object col[] = {"NIM" , "Nama" , "Alamat" , "Jenis Kelamin" , "Prodi/Jurusan" , "IPK"};
		model.setColumnIdentifiers(col);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	public void updateTable() 
	{
		conn = DataMahasiswa.ConnectDB();
		if (conn !=null)
		{
			String sql = "Select NIM, Nama, Alamat, Jenis Kelamin, Prodi/Jurusan, IPK";
		
		
		try 
		{
			pst = conn.prepareStatement(sql);
			rst = pst.executeQuery();
			Object[] columnData = new Object[6];
			
			while (rst.next()) {
				columnData [0] = rst.getString("NIM");
				columnData [1] = rst.getString("Nama");
				columnData [2] = rst.getString("Alamat");
				columnData [3] = rst.getString("Jenis Kelamin");
				columnData [4] = rst.getString("Prodi/Jurusan");
				columnData [5] = rst.getString("IPK");
				
				model.addRow(columnData);
			}
		}
		catch(Exception e) 
			{
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NIM");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(42, 147, 140, 23);
		frame.getContentPane().add(lblNewLabel);
		
		jtxtNIM = new JTextField();
		jtxtNIM.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jtxtNIM.setBounds(216, 144, 221, 28);
		frame.getContentPane().add(jtxtNIM);
		jtxtNIM.setColumns(10);
		
		JButton btnSimpan = new JButton("SIMPAN");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String sql = "INSERT INTO datamhs(NIM, Nama, Alamat, Jenis Kelamin, Prodi/Jurusan, IPK) VALUES (?,?,?,?,?,?)" ;
			
			try 
			{
				pst = conn.prepareStatement(sql);
				pst.setString(1, jtxtNIM.getText());
				pst.setString(2, jtxtNama.getText());
				pst.setString(3, jtxtAlamat.getText());
				pst.setString(4, jtxtJenisKelamin.getText());
				pst.setString(5, jtxtProdiJurusan.getText());
				pst.setString(6, jtxtIPK.getText());
				
				pst.execute();
				
				rst.close();
				pst.close();
			}
			catch (Exception ev)
			{
				JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
			}
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[] {
					
					jtxtNIM.getText(),
					jtxtNama.getText(),
					jtxtAlamat.getText(),
					jtxtJenisKelamin.getText(),
					jtxtProdiJurusan.getText(),
					jtxtIPK.getText(),
					
			});
			if (table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Membership Update Confirmed","Data Mahasiswa",
						JOptionPane.OK_OPTION);
					}
			 	}
			}
		});
			
		btnSimpan.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSimpan.setBounds(42, 518, 140, 39);
		frame.getContentPane().add(btnSimpan);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 94, 831, 592);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NIM", "Nama", "Alamat", "Jenis Kelamin", "Prodi/Jurusan", "IPK"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(129);
		table.getColumnModel().getColumn(2).setPreferredWidth(139);
		table.getColumnModel().getColumn(3).setPreferredWidth(77);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
		table.setFont(new Font("Times New Roman", Font.BOLD, 15));
		scrollPane.setViewportView(table);
		
		lblNewLabel_1 = new JLabel("Nama");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(44, 204, 140, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		jtxtNama = new JTextField();
		jtxtNama.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jtxtNama.setColumns(10);
		jtxtNama.setBounds(218, 201, 221, 28);
		frame.getContentPane().add(jtxtNama);
		
		lblNewLabel_2 = new JLabel("Alamat");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(43, 256, 140, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Jenis Kelamin");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(42, 313, 140, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		jtxtAlamat = new JTextField();
		jtxtAlamat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jtxtAlamat.setColumns(10);
		jtxtAlamat.setBounds(217, 253, 221, 28);
		frame.getContentPane().add(jtxtAlamat);
		
		jtxtJenisKelamin = new JTextField();
		jtxtJenisKelamin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jtxtJenisKelamin.setColumns(10);
		jtxtJenisKelamin.setBounds(216, 310, 221, 28);
		frame.getContentPane().add(jtxtJenisKelamin);
		
		lblNewLabel_4 = new JLabel("Prodi/Jurusan");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setBounds(42, 362, 140, 23);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("IPK");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBounds(44, 411, 140, 23);
		frame.getContentPane().add(lblNewLabel_5);
		
		jtxtProdiJurusan = new JTextField();
		jtxtProdiJurusan.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jtxtProdiJurusan.setColumns(10);
		jtxtProdiJurusan.setBounds(216, 359, 221, 28);
		frame.getContentPane().add(jtxtProdiJurusan);
		
		jtxtIPK = new JTextField();
		jtxtIPK.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jtxtIPK.setColumns(10);
		jtxtIPK.setBounds(218, 408, 221, 28);
		frame.getContentPane().add(jtxtIPK);
		
		btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				MessageFormat header = new MessageFormat("Printing in Progres");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");
				
				try
				{
					table.print();
				}
				catch(java.awt.print.PrinterException ev) {
					System.err.format("No Printer Found", ev.getMessage());
				}
			}
		});
		btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnPrint.setBounds(311, 518, 128, 39);
		frame.getContentPane().add(btnPrint);
		
		btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jtxtNIM.setText(null);
				jtxtNama.setText(null);
				jtxtAlamat.setText(null);
				jtxtJenisKelamin.setText(null);
				jtxtProdiJurusan.setText(null);
				jtxtIPK.setText(null);
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnReset.setBounds(42, 594, 140, 39);
		frame.getContentPane().add(btnReset);
		
		btnKeluar = new JButton("KELUAR");
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame = new JFrame ("Keluar");
						if (JOptionPane.showConfirmDialog(frame, "Apakah anda yakin ingin keluar", "Data Mahasiswa", 
								JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
							System.exit(0);
						}
			}
		});
		btnKeluar.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnKeluar.setBounds(311, 594, 128, 39);
		frame.getContentPane().add(btnKeluar);
		
		JLabel lblNewLabel_6 = new JLabel("DATABASE MAHASISWA");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_6.setBounds(510, 24, 380, 39);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Silahkan Masukkan Data Dengan Benar");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_7.setBounds(42, 74, 354, 39);
		frame.getContentPane().add(lblNewLabel_7);
	}
}
