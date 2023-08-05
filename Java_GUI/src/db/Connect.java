package db;

import java.sql.*;

public class Connect {
	
	public Connection connect;
	
	public ResultSet rs;
	public PreparedStatement ps;

	public Connect() {
		try {
			String url = "jdbc:mysql://localhost:3306/pudding_menu";
			String username = "root";
			String password = "";
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getMenu() {
		try {
			ps = connect.prepareStatement("SELECT * FROM pudding_menu");
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet insertMenu(String kode, String nama, int harga, int stok) {
		try {
			ps = connect.prepareStatement("INSERT INTO pudding_menu (`Kode Menu`, `Nama Menu`, `Harga Menu`, `Stok Menu`) VALUES (?,?,?,?)");
			ps.setString(1, kode);
			ps.setString(2, nama);
			ps.setInt(3, harga);
			ps.setInt(4, stok);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet deleteMenu(String kode) {
		try {
			ps = connect.prepareStatement("DELETE FROM pudding_menu WHERE `Kode Menu` = (?)");
			ps.setString(1, kode);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet updateMenu(int harga, int stok, String kode) {
		try {
			ps = connect.prepareStatement("UPDATE pudding_menu SET `Harga Menu` = ? , `Stok Menu` = ? WHERE `Kode Menu` = ?");
			ps.setInt(1, harga);
			ps.setInt(2, stok);
			ps.setString(3, kode);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}
