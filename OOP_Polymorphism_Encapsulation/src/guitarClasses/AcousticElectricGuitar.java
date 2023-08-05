package guitarClasses;

import pickUpClasses.GuitarPickup;

/*
 * Class ini untuk gitar jenis Acoustic Electric yang menginherit Abstract class GuitarAbstract
 * Class ini memiliki pickup
 * Class ini akan memiliki implementasi displayInfo() nya sendiri dengan melakukan override..
 * ..method yang ada di GuitarAbstract
 */

public class AcousticElectricGuitar extends GuitarAbstract{
	//Aggregation
	private GuitarPickup pickup;
	/*
	 * Class ini menggunakan aggregation dengan memasukkan class GuitarPickup
	 * Hal ini dikarenakan pada soal disebutkan bahwa "Acoustic Electric Guitar has a pickup"
	 * tetapi "we can hear its sound without pickup"
	 * Maka, bisa disimpulkan bahwa AcousticElectricGuitar has a GuitarPickup, tetapi keduanya tetap bisa exist tanpa satu sama lain
	 */
	
	//Constructor
	public AcousticElectricGuitar(GuitarPickup pickup) {
		super("Acoustic Electric Guitar", "Crunch", true); 
		//Semua argumen pada super constructornya adalah default value untuk Acoustic Electric guitar
		//tone dan volume ada di pickup
		this.pickup = pickup;
	}
	
	//Setters & Getters
	public GuitarPickup getPickup() {
		return pickup;
	}
	
	public void setPickup(GuitarPickup pickup) {
		this.pickup = pickup;
	}
	
	//Override displayInfo() menampilkan type, soundType, tone, dan volume
	@Override
	public void displayInfo() {
		System.out.println(type + " - " + soundType + " - " + pickup.getTone() + " - " + pickup.getVolume());
	}

}
