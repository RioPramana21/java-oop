package classes;

import java.util.ArrayList;

public class Customer {

	private String name;
	private final ArrayList<Headset> collection;
	
	public Customer(String name, ArrayList<Headset> collection) {
		super();
		this.name = name;
		this.collection = collection;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Headset> getCollection() {
		return collection;
	}
	
	//method nambah headset ke collection
	public void addToCollection(Headset h) {
		this.collection.add(h);
	}

}
