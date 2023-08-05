package classes;

public abstract class Furniture {
	private String id, name, quality;
	private int size;
	
	public abstract void printData();
	public abstract int getTotalPrice();
	
	public Furniture(String id, String name, String quality, int size) {
		super();
		this.id = id;
		this.name = name;
		this.quality = quality;
		this.size = size;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
