package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import guitarClasses.*;
import pickUpClasses.*;

/* Overview explanation of the whole program will be on the bottom of the main program code
 * Detailed explanations are available throughout the main program code and each class code
 */

public class Main {
	//2440016804 - Rio Pramana - LC01 - Mid Exam OOP
	
	Scanner in = new Scanner(System.in);
	Random rand = new Random();
	//ArrayList untuk menyimpan semua gitar yang sudah di build
	ArrayList<GuitarAbstract> guitarList = new ArrayList<GuitarAbstract>();
	
	private void cls() { //Hanya method untuk merapikan display, berfungsi seperti system("cls") di bahasa C
		for (int i = 0; i < 21; i++) {
			System.out.println();
		}
	}

	public Main() {
		mainMenu();
	}
	
	private void mainMenu() {
		int menu = 0;
		do {
			System.out.println("1. Build Guitar");
			System.out.println("2. Display List of Guitar");
			System.out.println("3. Exit");
			System.out.print("Choose menu[1..3]: ");
			menu = in.nextInt(); in.nextLine();
			cls();
			switch (menu) {
			case 1:
				buildGuitar();
				break;
			case 2:
				displayGuitarList();
				break;
			}
			cls();
		} while (menu != 3);
		//Exit program message:
		System.out.println("Thank you for using this program");
		System.out.println("2440016804 - Rio Pramana - LC01 - Mid Exam OOP");
	}

	//Menu 1 (Build Guitar)
	private void buildGuitar() {
		int guitar;
		cls();
		System.out.println("1. Acoustic");
		System.out.println("2. Electric");
		System.out.println("3. Acoustic Electric");
		System.out.print("Input guitar type[1..3]: ");
		guitar = in.nextInt(); in.nextLine();
		switch (guitar) {
		case 1: //Acoustic
			int sound = rand.nextInt(2); //random integer 0-1
			/*
			 * Jika int sound = 0, maka soundType = Warm
			 * Jika int sound = 1, maka soundType = Bright
			 */
			String soundType = (sound == 1) ? "Bright" : "Warm";
			guitarList.add(new AcousticGuitar(soundType));
			break;
		case 2: //Electric
			int switchPositionNumber, tone, volume;
			System.out.print("Input switch position: ");
			switchPositionNumber = in.nextInt(); in.nextLine();
			System.out.print("Input tone: ");
			tone = in.nextInt(); in.nextLine();
			System.out.print("Input volume: ");
			volume = in.nextInt(); in.nextLine();
			guitarList.add(new ElectricGuitar(new ElectricGuitarPickup(tone, volume, switchPositionNumber)));
			break;
		case 3: //Acoustic Electric
			guitarList.add(new AcousticElectricGuitar(new GuitarPickup(0, 0)));
			break;
		}
		System.out.println("Successfully built a guitar!");
		System.out.println("Press enter to continue..."); in.nextLine();
	}

	//Menu 2 (Display List of Guitar)
	private void displayGuitarList() {
		if (guitarList.isEmpty()) System.out.println("There are no guitar yet..."); //Jika belum ada guitar yang di build, print message
		else {
			System.out.println("[GuitarType] - [GuitarSoundType] - [Tone] - [Volume] - [SwitchPosition]");
			for (GuitarAbstract guitar : guitarList) { //Display info setiap gitar pada guitarList
				guitar.displayInfo();
				//displayInfo() has been overridden on each subclass
				//so it will display the right info according to the type of guitar automatically without having to downcast the class manually
			}
		}
		System.out.println("Press enter to continue..."); in.nextLine();
	}

	public static void main(String[] args) {
		new Main();
	}

}

/*
 * Program ini dibuat dengan 3 packages untuk keperluan inheritance dan memastikan encapsulation tetap terjaga
 * Program ini dibuat dengan mengimplementasikan Abstract Class dan Inheritance pada classnya
 * Sedangkan untuk Polymorphism, diimplementasikan dengan override method displayInfo() untuk menampilkan info setiap jenis gitar yang berbeda
 * Terdapat aggregation dan composition yang diimplementasikan pada class ElectricGuitar (composition) 
 * dan AcousticElectricGuitar (aggregation)
 */
