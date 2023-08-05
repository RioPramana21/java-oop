package classes;

public class Transaction {
	private String furnitureId, email;
	private int qty, id;
	
	public Transaction(String furnitureId, String email, int qty, int id) {
		super();
		this.furnitureId = furnitureId;
		this.email = email;
		this.qty = qty;
		this.id = id;
	}

	public void printTransactionData() {
		System.out.printf("| %d | %s | %d | %s |", id, email, qty, furnitureId);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFurnitureId() {
		return furnitureId;
	}
	public void setFurnitureId(String furnitureId) {
		this.furnitureId = furnitureId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
