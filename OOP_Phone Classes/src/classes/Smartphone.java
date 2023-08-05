package classes;

public abstract class Smartphone {

	private String type, cord, address;
	private int price, discount;
	
	public abstract void printDetails();
	public abstract void calculateDiscount();
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCord() {
		return cord;
	}
	public void setCord(String cord) {
		this.cord = cord;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public Smartphone(String type, String cord, String address, int price) {
		super();
		this.type = type;
		this.cord = cord;
		this.address = address;
		this.price = price;
		this.discount = 0; //dianggap 0 sebagai default, nanti akan di set oleh masing-masing child
	}
	
}
