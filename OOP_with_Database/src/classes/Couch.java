package classes;

public class Couch extends Furniture{
	private boolean hasArmRest, hasLegExtension;

	public Couch(String id, String name, String quality, int size, boolean hasArmRest, boolean hasLegExtension) {
		super(id, name, quality, size);
		this.hasArmRest = hasArmRest;
		this.hasLegExtension = hasLegExtension;
	}

	public boolean isHasArmRest() {
		return hasArmRest;
	}

	public void setHasArmRest(boolean hasArmRest) {
		this.hasArmRest = hasArmRest;
	}

	public boolean isHasLegExtension() {
		return hasLegExtension;
	}

	public void setHasLegExtension(boolean hasLegExtension) {
		this.hasLegExtension = hasLegExtension;
	}

	@Override
	public void printData() {
		String armRest = (hasArmRest) ? "Yes" : "No";
		String legExt = (hasLegExtension) ? "Yes" : "No";
		System.out.printf("|%s |%s      |%d  |%s    |%s   |%s   |\n", this.getId(), this.getName(), this.getSize(), this.getQuality(), armRest, legExt);
	}

	@Override
	public int getTotalPrice() {
		int additional = 0;
		if (hasArmRest) additional += 35000;
		if (hasLegExtension) additional += 50000;
		return 75000 + additional;
	}
	
}
