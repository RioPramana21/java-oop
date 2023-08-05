package guitarClasses;

/*
 * Class ini untuk gitar jenis Acoustic yang menginherit Abstract class GuitarAbstract
 * Class ini tidak memiliki atribut uniknya sendiri karena atribut yang dibutuhkan di program untuk Acoustic Gitar..
 * .. sudah ada di GuitarAbstract semua
 * Perbedaannya adalah class ini akan memiliki implementasi displayInfo() nya sendiri dengan melakukan override..
 * ..method yang ada di GuitarAbstract
 */

public class AcousticGuitar extends GuitarAbstract{
	
	public AcousticGuitar(String soundType) { //Randomizing soundType will be in the main program
		super("Acoustic Guitar", soundType, false); //type guitarnya sudah pasti acoustic dan hasPickup nya sudah pasti false
	}

	@Override
	public void displayInfo() {
		System.out.println(type + " - " + soundType); //Hanya display type dan soundType
	}
	
}
