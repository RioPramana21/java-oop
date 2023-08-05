package classes;

import java.util.Random;

public class Samsuing extends Smartphone{

	private String samsuingType;

	public String getSamsuingType() {
		return samsuingType;
	}

	public void setSamsuingType(String samsuingType) {
		this.samsuingType = samsuingType;
	}

	public Samsuing(String type, String cord, String address, String samsuingType) {
		super(type, cord, address, 15000000);
		this.samsuingType = samsuingType;
		calculateDiscount();
	}

	@Override
	public void printDetails() {
		System.out.println(this.getCord() + " |    -    | " + samsuingType + " | " + this.getAddress() + " | Rp. " + (this.getPrice()-this.getDiscount()));
	}

	@Override
	public void calculateDiscount() {
		Random rand = new Random();
		if (samsuingType.equals("Alries")) {
			this.setDiscount(rand.nextInt(500001));
		}
		else if (samsuingType.equals("Saries")) {
			this.setDiscount(rand.nextInt(750001));
		}
		else if (samsuingType.equals("Xseries")) {
			this.setDiscount(rand.nextInt(1000001));
		}
	}
	
}
