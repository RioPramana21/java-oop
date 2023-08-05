package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Bed;
import classes.Couch;
import classes.Furniture;
import classes.Transaction;

public class QueryDB {
	Connect con = Connect.getConnection();
	String query;
	ResultSet rs;
	
	public ArrayList<Furniture> getFurnituresData(){
		ArrayList<Furniture> temp = new ArrayList<>();
		String id, name, quality;
		int size;
		boolean feature1, feature2;
		for (int i = 0; i < 2; i++) {
			query = (i == 0) ? "SELECT * FROM `bedfurniture`" : "SELECT * FROM `couchfurniture`";
			rs = con.executeQuery(query);
			try {
				while(rs.next()) {
					id = rs.getString(1);
					name = rs.getString(2);
					size = rs.getInt(3);
					quality = rs.getString(4);
					feature1 = rs.getBoolean(5);
					if (i == 0) temp.add(new Bed(id, name, quality, size, feature1));
					else if (i == 1) {
						feature2 = rs.getBoolean(6);
						temp.add(new Couch(id, name, quality, size, feature1, feature2));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	public ArrayList<Transaction> getTransactionsData(){
		ArrayList<Transaction> temp = new ArrayList<>();
		String email, furnitureId;
		int qty, id;
		query = "SELECT t.TransactionID, t.UserEmail,t.FurnitureID,t.Quantity FROM `transaction` t \r\n" + 
				"JOIN bedfurniture b ON b.FurnitureID = t.FurnitureID\r\n";
		rs = con.executeQuery(query);
		try {
			while(rs.next()) {
				id = rs.getInt("TransactionID");
				email = rs.getString("UserEmail");
				qty = rs.getInt("Quantity");
				furnitureId = rs.getString("FurnitureID");
				temp.add(new Transaction(furnitureId, email, qty, id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		query = "SELECT t.TransactionID, t.UserEmail,t.FurnitureID,t.Quantity FROM `transaction` t \r\n" + 
				"JOIN couchfurniture c ON c.FurnitureID = t.FurnitureID\r\n";
		rs = con.executeQuery(query);
		try {
			while(rs.next()) {
				id = rs.getInt("TransactionID");
				email = rs.getString("UserEmail");
				qty = rs.getInt("Quantity");
				furnitureId = rs.getString("FurnitureID");
				temp.add(new Transaction(furnitureId, email, qty, id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public String getFurnitureNameById(String id) {
		String temp = null;
		query = String.format("SELECT * FROM `bedfurniture` b WHERE b.FurnitureID = '%s'", id);
		rs = con.executeQuery(query);
		try {
			if(rs.next()) {
				temp = rs.getString("FurnitureName");
			}
			else {
				query = String.format("SELECT * FROM `couchfurniture` c WHERE c.FurnitureID = '%s'", id);
				rs = con.executeQuery(query);
				if (rs.next()) {
					return rs.getString("FurnitureName");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public void deleteTransaction(int id) {
		query = String.format("DELETE FROM `transaction` WHERE TransactionID = %d", id);
		con.executeUpdate(query);
	}
	
	public void insertTransaction(String furnitureId, String email, int qty) {
		query = String.format("INSERT INTO `transaction`(`UserEmail`, `Quantity`, `FurnitureID`) VALUES ('%s',%d,'%s')", email, qty, furnitureId);
		con.executeUpdate(query);
	}
}
