package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import db.Connect;

public class PuddingMenu extends JFrame implements ActionListener{
	
	Connect db = new Connect();
	Random rand = new Random();
	Vector<String> idList = new Vector<String>();
	
	JLabel titleLabel, dataLabel, viewLabel, updateLabel, namaLabel, hargaLabel, stokLabel;
	JTextField namaField, hargaField, stokField;
	JButton addBtn, deleteBtn, updateBtn;
	
	JLabel updateHargaLabel, updateStokLabel; //biar enak dilihat & dibedain, ini khusus update
	JTextField updateHargaField, updateStokField;
	
	JLabel keteranganDeleteLabel, keteranganUpdateLabel;
	
	//table
	JTable menuTable;
	DefaultTableModel dtm;
	JScrollPane jsp;
	Vector<String> columnName = new Vector<>();
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();

	public PuddingMenu() {
		setFrame();
		getData();
		setGUI();
	}
	
	public void setFrame() {
		this.setVisible(true);
		this.setSize(1200,650);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("PT Pudding Menu");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	public void setGUI() {
		
		//judul
		titleLabel = new JLabel("PT Pudding Menu"); 
		titleLabel.setBounds(450, 20, 300, 50);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
		this.add(titleLabel);
		
		//menu
		dataLabel = new JLabel("Insert/Delete Menu");
		dataLabel.setBounds(65, 100, 200, 30);
		dataLabel.setFont(new Font("Serif", Font.BOLD, 20));
		this.add(dataLabel);
		
		viewLabel = new JLabel("View Menu");
		viewLabel.setBounds(500, 100, 150, 30);
		viewLabel.setFont(new Font("Serif", Font.BOLD, 20));
		this.add(viewLabel);
		
		updateLabel = new JLabel("Update Menu");
		updateLabel.setBounds(930, 100, 150, 30);
		updateLabel.setFont(new Font("Serif", Font.BOLD, 20));
		this.add(updateLabel);
		
		//insert nama
		namaLabel = new JLabel("Nama   :");
		namaLabel.setBounds(20,140,150,30); // (margin-x, margin-y, width, height)
		this.add(namaLabel);
		
		namaField = new JTextField();
		namaField.setBounds(100, 140, 200,30);
		this.add(namaField);
		
		//insert harga
		hargaLabel = new JLabel("Harga  :");
		hargaLabel.setBounds(20, 190, 150, 30);
		this.add(hargaLabel);
		
		hargaField = new JTextField();
		hargaField.setBounds(100, 190, 200,30);
		this.add(hargaField);
		
		//insert stok
		stokLabel = new JLabel("Stok    :");
		stokLabel.setBounds(20, 240, 150,30);
		this.add(stokLabel);
		
		stokField = new JTextField();
		stokField.setBounds(100, 240, 200, 30);
		this.add(stokField);
		
		//update harga
		updateHargaLabel = new JLabel("Harga  :");
		updateHargaLabel.setBounds(850, 140, 150, 30);
		this.add(updateHargaLabel);
		
		updateHargaField = new JTextField();
		updateHargaField.setBounds(930, 140, 200,30);
		this.add(updateHargaField);
		
		//update stok
		updateStokLabel = new JLabel("Stok    :");
		updateStokLabel.setBounds(850, 190, 150,30);
		this.add(updateStokLabel);
		
		updateStokField = new JTextField();
		updateStokField.setBounds(930, 190, 200, 30);
		this.add(updateStokField);
		
		//keterangan delete
		keteranganDeleteLabel = new JLabel();
		keteranganDeleteLabel.setText("<html>P.S. To delete a menu, select the menu you wish to delete in view menu table<html>");
		keteranganDeleteLabel.setBounds(50, 300, 275, 100);
		this.add(keteranganDeleteLabel);
		
		//keterangan update
		keteranganDeleteLabel = new JLabel();
		keteranganDeleteLabel.setText("<html>P.S. To update a menu, select the menu you wish to update first in view menu table before entering the input<html>");
		keteranganDeleteLabel.setBounds(800, 300, 375, 100);
		this.add(keteranganDeleteLabel);
		
		//button
		addBtn = new JButton("Add");
		addBtn.setBounds(50,290,100,30);
		addBtn.addActionListener(this);
		this.add(addBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(200,290,100,30);
		deleteBtn.addActionListener(this);
		this.add(deleteBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(930,290,100,30);
		updateBtn.addActionListener(this);
		this.add(updateBtn);
		
		//table
		columnName.add("Kode");
		columnName.add("Nama");
		columnName.add("Harga");
		columnName.add("Stok");
		
		dtm = new DefaultTableModel(data, columnName);
		menuTable = new JTable(dtm);
		jsp = new JScrollPane(menuTable);
		jsp.setBounds(350,140,400,450);
		this.add(jsp);
	}

	public void getData() {
		db.rs = db.getMenu();
		try {
			while(db.rs.next()) {
				Vector<Object> newRow = new Vector<>();
				newRow.add(db.rs.getString("Kode Menu"));
				newRow.add(db.rs.getString("Nama Menu"));
				newRow.add(db.rs.getInt("Harga Menu"));
				newRow.add(db.rs.getInt("Stok Menu"));
				
				data.add(newRow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBtn) {
			add();
		}else if (e.getSource() == deleteBtn) {
			delete();
		}else if (e.getSource() == updateBtn) {
			update();
		}
	}
	
	private void update() {
		int index = menuTable.getSelectedRow();
		String kode = String.valueOf(dtm.getValueAt(index, 0));
		String harga = updateHargaField.getText();
		String stok = updateStokField.getText();
		
		//validasi numerical harga & stok
		if (isNumeric(harga) && isNumeric(stok)) {
			//insert db
			db.updateMenu(Integer.parseInt(harga), Integer.parseInt(stok), kode); //update db
			JOptionPane.showMessageDialog(this, "Menu with kode " + kode + " has been updated!");
			dtm.setValueAt(harga, index, 2);
			dtm.setValueAt(stok, index, 3);
		} else {
			JOptionPane.showMessageDialog(this, "Harga & Stok must be numerical");
		}
	}
	
	public boolean isNumeric(String s) {
		int len = s.length();
		final String digit = "0123456789";
		Character c;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			//kalau c tidak ada di string digit berarti berarti bukan numeric
			//asumsi numeric yang diterima adalah digit 0-9 (bukan arabic number dkk)
			if (!digit.contains(c.toString())) return false;
		}
		return true;
	}

	public void add() {
		if (idList.size() == 999) {
			JOptionPane.showMessageDialog(this, "Sorry, the menu list is full");
		} else {
			//generate kode menu
			String kode;
			do {
				kode = generateCode();
			} while (!isUnique(kode));
			
			//get input
			String nama = namaField.getText();
			String harga = hargaField.getText();
			String stok = stokField.getText();
			
			//validasi numerical harga & stok
			if (isNumeric(harga) && isNumeric(stok)) {
				//insert db
				db.insertMenu(kode, nama, Integer.parseInt(harga), Integer.parseInt(stok)); //add to db
				JOptionPane.showMessageDialog(this, "New menu added!");
				
				//biar langsung bisa di view addnya
				Object row[] = {kode, nama, harga, stok}; //add to dtm
				dtm.addRow(row);
			} else {
				JOptionPane.showMessageDialog(this, "Harga & Stok must be numerical");
			}
		}
	}
	
	private String generateCode() {
		final String number = "0123456789";
		StringBuilder kode = new StringBuilder();
		kode.append("PD-");
//		Generate 3 random integer for the code
		for (int i = 0; i < 3; ++i) {
			kode.append(number.charAt(rand.nextInt(number.length())));
		}
		return kode.toString();
	}
	
	private boolean isUnique(String kode) {
		for (String s : idList) {
			if (s.equals(kode)) return false;
		}
		return true;
	}

	public void delete() {
		int index = menuTable.getSelectedRow(); //dpt index yg dipilih
		String kode = String.valueOf(dtm.getValueAt(index, 0));
		db.deleteMenu(kode);
		JOptionPane.showMessageDialog(this, "Menu deleted!");
		dtm.removeRow(index);
	}

}
