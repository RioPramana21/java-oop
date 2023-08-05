package pickUpClasses;

/*
 * Class ini dibuat sebagai pickup dari Acoustic Electric Guitar
 * Class ini memiliki tone dan volume
 */

public class GuitarPickup {
	//Menggunakan protected karena akan di inherit oleh ElectricGuitarPickup
	protected int tone;
	protected int volume;
	
	public GuitarPickup(int tone, int volume) {
		super();
		this.tone = tone;
		this.volume = volume;
	}
	
	public int getTone() {
		return tone;
	}
	public void setTone(int tone) {
		this.tone = tone;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
}
