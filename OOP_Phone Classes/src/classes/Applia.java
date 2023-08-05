package classes;

import java.util.Random;

public class Applia extends Smartphone{

	private String appliaType;
	
	public String getAppliaType() {
		return appliaType;
	}

	public void setAppliaType(String appliaType) {
		this.appliaType = appliaType;
	}
	
	public Applia(String type, String cord, String address, String appliaType) {
		super(type, cord, address, 17000000);
		this.appliaType = appliaType;
		calculateDiscount();
	}

	@Override
	public void printDetails() {
		System.out.println(this.getCord() + " | " + appliaType + " |    -    | " + this.getAddress() + " | Rp. " + (this.getPrice()-this.getDiscount()));
	}

	@Override
	public void calculateDiscount() {
		Random rand = new Random();
		if (appliaType.equals("Original")) {
			this.setDiscount(rand.nextInt(1000001));
		}
		else if (appliaType.equals("Pro")) {
			this.setDiscount(rand.nextInt(2000001));
		}
		else if (appliaType.equals("Promax")) {
			this.setDiscount(rand.nextInt(2500001));
		}
	}
	
}
