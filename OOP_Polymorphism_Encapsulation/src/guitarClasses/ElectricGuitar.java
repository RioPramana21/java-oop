package guitarClasses;

import pickUpClasses.ElectricGuitarPickup;

/*
 * Class ini untuk gitar jenis Electric yang menginherit Abstract class GuitarAbstract
 * Class ini memiliki pickup (khusus electric guitar)
 * Class ini akan memiliki implementasi displayInfo() nya sendiri dengan melakukan override..
 * ..method yang ada di GuitarAbstract
 */

public class ElectricGuitar extends GuitarAbstract{

	//Composition
	private final ElectricGuitarPickup pickup;
	/*
	 * Class ini menggunakan composition dengan memasukkan class ElectricGuitarPickup
	 * Hal ini dikarenakan pada soal disebutkan bahwa "Pickup is a must in electric guitar"
	 * Maka, ElectricGuitar tidak akan bisa exist tanpa adanya ElectricGuitarPickup
	 */
	
	//Constructor
	public ElectricGuitar(ElectricGuitarPickup pickup) {
		super("Electric Guitar", pickup.getSoundType(), true); //Type guitar sudah pasti electric dan hasPickup = true
		//tone, volume, dan switch position serta soundType ada di pickup
		this.pickup = pickup;
	}

	//Setters & Getters
	public ElectricGuitarPickup getPickup() {
		return pickup;
	}
	
	//Override displayInfo() menampilkan type, soundType, tone, volume, dan switch position
	@Override
	public void displayInfo() {
		System.out.println(type + " - " + soundType + " - " + pickup.getTone() + " - " + pickup.getVolume() + " - " + pickup.getSwitchPosition());
	}

}
