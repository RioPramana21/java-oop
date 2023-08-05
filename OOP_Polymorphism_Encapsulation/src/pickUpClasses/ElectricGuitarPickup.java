package pickUpClasses;

/*
 * Class ini dibuat sebagai pickup dari Electric Guitar
 * Class ini menginherit tone dan volume
 * serta memiliki switchPosition yang akan menentukan soundTypenya
 */

public class ElectricGuitarPickup extends GuitarPickup{
	private int switchPosition;
	private String soundType;
	
	public ElectricGuitarPickup(int tone, int volume, int switchPosition) {
		super(tone, volume);
		this.switchPosition = switchPosition;
		//Untuk reduce lines of code, menggunakan array untuk menentukan soundType berdasarkan switchPosition
		String[] soundTypes = {"Funk", "Jazz", "Blues", "Rock"};
		this.soundType = soundTypes[switchPosition-1];
	}
	
	public int getSwitchPosition() {
		return switchPosition;
	}
	public void setSwitchPosition(int switchPosition) {
		this.switchPosition = switchPosition;
	}
	public String getSoundType() {
		return soundType;
	}
	public void setSoundType(String soundType) {
		this.soundType = soundType;
	}
	
}
