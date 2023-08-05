package classes;

public class Bed extends Furniture{
	private boolean foldable;

	public Bed(String id, String name, String quality, int size, boolean foldable) {
		super(id, name, quality, size);
		this.foldable = foldable;
	}

	public boolean isFoldable() {
		return foldable;
	}

	public void setFoldable(boolean foldable) {
		this.foldable = foldable;
	}

	@Override
	public void printData() {
		String foldableStr = (foldable) ? "Yes" : "No";
		System.out.printf("|%s |%s      |%d  |%s    |%s   |\n", this.getId(), this.getName(), this.getSize(), this.getQuality(), foldableStr);
	}

	@Override
	public int getTotalPrice() {
		int additional = (foldable) ? 100000 : 0;
 		return 100000 + additional;
	}
	
}
