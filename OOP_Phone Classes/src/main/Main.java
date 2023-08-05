package main;

import java.util.ArrayList;
import java.util.Scanner;

import classes.*;

//2440016804 - Rio Pramana - BE01 - Quiz 2 OOP
public class Main {
	
	Scanner in = new Scanner(System.in);
	ArrayList<Smartphone> smartphones = new ArrayList<>();
	
	private void cls() {
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
			System.out.println("BakSmartphone");
			System.out.println("===================");
			System.out.println("1. Search Smartphone");
			System.out.println("2. View All Smartphone");
			System.out.println("3. Buy Smartphone");
			System.out.println("4. Exit");
			System.out.print(">> ");
			menu = in.nextInt(); in.nextLine();
			cls();
			switch (menu) {
			case 1:
				search();
				cls();
				break;
			case 2:
				view();
				System.out.println();
				System.out.println("Press enter to continue..."); in.nextLine();
				cls();
				break;
			case 3:
				buy();
				cls();
				break;
			}
		} while (menu != 4);
		System.out.println("Thank you for buying smartphone at BakSmartphone");
	}
	
	private boolean isAlphanumeric(String s) {
		final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String digit = "0123456789";
		//asumsi alphanumericnya adalah huruf latin dan angka 0-9 saja (tidak include seperti arabic number dkk)
		int len = s.length();
		boolean containsDigit = false, containsAlphabet = false;
		Character c;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (alphabet.contains(c.toString())) containsAlphabet = true;
			else if (digit.contains(c.toString())) containsDigit = true;
			if (containsAlphabet && containsDigit) return true;
		}
		return false;
	}

	private void search() {
		int choose = 0;
		String smartphoneType, specificType, cord, address;
		Smartphone s = null;
		System.out.println("Search Smartphone");
		System.out.println("=================");
		System.out.println("1. Applia");
		System.out.println("2. Samsuing");
		do {
			System.out.print(">> ");
			choose = in.nextInt(); in.nextLine();
		} while (choose != 1 && choose != 2);
		cls();
		if (choose == 1) {
			smartphoneType = "Applia";
			do {
				System.out.print("Do you want to include your applia with charging cord?[Yes | No](Case Insensitive): ");
				cord = in.nextLine();
			} while (!(cord.equalsIgnoreCase("Yes") || cord.equalsIgnoreCase("No")));
			do {
				System.out.print("Type of applia phone you want to buy?[Original | Pro | Promax](Case Sensitive): ");
				specificType = in.nextLine();
			} while (!(specificType.equals("Original") || specificType.equals("Pro") || specificType.equals("Promax")));
			do {
				System.out.print("Input your house address[must be alphanumeric]: ");
				address = in.nextLine();
			} while (!isAlphanumeric(address));
			System.out.println();
			s = new Applia(smartphoneType, cord, address, specificType);
			System.out.println("Adding applia completed!");
			System.out.println("Congratulations you got a discount of " + s.getDiscount());
		}
		else if (choose == 2) {
			smartphoneType = "Samsuing";
			do {
				System.out.print("Do you want to include your samsuing with charging cord?[Yes | No](Case Insensitive): ");
				cord = in.nextLine();
			} while (!(cord.equalsIgnoreCase("Yes") || cord.equalsIgnoreCase("No")));
			do {
				System.out.print("Type of samsuing phone you want to buy?[Alries | Saries | Xseries](Case Sensitive): ");
				specificType = in.nextLine();
			} while (!(specificType.equals("Alries") || specificType.equals("Saries") || specificType.equals("Xseries")));
			do {
				System.out.print("Input your house address[must be alphanumeric]: ");
				address = in.nextLine();
			} while (!isAlphanumeric(address));
			System.out.println();
			s = new Samsuing(smartphoneType, cord, address, specificType);
			System.out.println("Adding samsuing completed!");
			System.out.println("Congratulations you got a discount of " + s.getDiscount());
		}
		smartphones.add(s);
		System.out.println();
		System.out.println("Press enter to continue..."); in.nextLine();
	}

	private void view() {
		if (smartphones.isEmpty()) System.out.println("There is no smartphone...");
		else {
			//Setiap atribut dipisahkan dengan |
			System.out.println("==========================================================================");
			System.out.println("|No | Cord | Applia Type | Samsuing Type | Address | Price |");
			System.out.println("==========================================================================");
			int i = 0;
			for (Smartphone s : smartphones) {
				i++;
				System.out.print("|" + i + " | ");
				s.printDetails();
			}
			System.out.println("==========================================================================");
		}
	}

	private void buy() {
		view();
		if (!smartphones.isEmpty()) {
			Smartphone temp;
			System.out.println();
			int delete, size = smartphones.size();
			do {
				System.out.print("Input the number of index you want to delete [1 .. " + size + "]: ");
				delete = in.nextInt(); in.nextLine();
			} while (delete < 1 || delete > size);
			temp = smartphones.get(delete - 1);
			System.out.println("Total Price: " + (temp.getPrice() - temp.getDiscount()));
			//Untuk total price saya tidak menyimpan atribut khusus totalPrice
			//Agar totalPrice bisa menyesuaikan dengan original price dan discount..
			//..seandainya terjadi perubahan discount atau original price misalnya, jadi tidak perlu mengubah totalPrice secara manual di setiap objectnya
			//price == original price
			smartphones.remove(temp);
			System.out.println("Thank you for buying smartphone at BakSmartphone");
		}
		System.out.println();
		System.out.println("Press enter to continue..."); in.nextLine();
	}

	public static void main(String[] args) {
		new Main();
	}

}
