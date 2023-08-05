package guitarClasses;

/*
 * Abstract class ini hanya mengandung atribut dan method yang semua jenis gitar miliki, terutama di program ini
 * Memilih menggunakan abstract karena di program ini tidak akan dibuat object Guitar sehingga ini hanya menjadi..
 * ..blueprint bagi tiap jenis gitar yang akan dibuat objectnya nanti
 * Abstract class ini menjadi parent class yang akan di inherit oleh child classnya
 */

public abstract class GuitarAbstract {
	protected String type; //Jenis gitar seperti Acoustic, Electric
	protected String soundType; //Sound type seperti Warm, Jazz
	protected boolean hasPickup; // Ada gitar yang memiliki pickup, ada yang tidak
	
	//Display info gitar, tetapi implementasinya tergantung child class jenis gitarnya
	//..karena setiap jenis gitar memiliki info berbeda yang akan di display
	public abstract void displayInfo();

	//Setters & Getters
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSoundType() {
		return soundType;
	}

	public void setSoundType(String soundType) {
		this.soundType = soundType;
	}

	public boolean isHasPickup() {
		return hasPickup;
	}

	public void setHasPickup(boolean hasPickup) {
		this.hasPickup = hasPickup;
	}

	//Constructor
	public GuitarAbstract(String type, String soundType, boolean hasPickup) {
		super();
		this.type = type;
		this.soundType = soundType;
		this.hasPickup = hasPickup;
	}
	
}