package classes;

public class Headset {

	private String name;
	private String type;
	private int price;
	
	public Headset(String name, String type) {
		super();
		this.name = name;
		this.type = type;
		updatePrice(type);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void updatePrice(String type) {
		//Untuk set price ketika initialization dan update dilakukan
		if(this.type.equalsIgnoreCase("Headphone")) this.price = 20000;
		else if(this.type.equalsIgnoreCase("IEM")) this.price = 25000;
		else if(this.type.equalsIgnoreCase("OnEar")) this.price = 30000;
	}

}
