package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import classes.Customer;
import classes.Headset;

public class Main {
//	2440016804 - BE01 - Rio Pramana - Quiz 1 OOP
	
	Scanner in = new Scanner(System.in);
	Random rand = new Random();
	ArrayList<Headset> collection = new ArrayList<Headset>();
	ArrayList<Headset> listHeadset = new ArrayList<Headset>();
	Customer customer;
	
	public Main() {
		initCustomer();
		mainMenu();
	}
	
	private void cls() {
		for (int i = 0; i < 21; i++) {
			System.out.println();
		}
	}

	private void initCustomer() {
		String name;
		System.out.println("BakHeadset");
		System.out.println("==========");
		System.out.println();
		do {
			System.out.print("Input customer name [5..20]: ");
			name = in.nextLine();
		} while (name.length() < 5 || name.length() > 20);
		customer = new Customer(name, collection);
		System.out.println();
		System.out.println("Welcome, " + name);
		System.out.println();
		System.out.println("Press enter to continue to main menu..."); in.nextLine();
	}

	private void mainMenu() {
		int menu = 0;
		do {
			cls();
			System.out.println("BakHeadset");
			System.out.println("==========");
			System.out.println();
			System.out.println("Hello, " + customer.getName());
			System.out.println();
			System.out.println("Menu:");
			System.out.println("1. Add Headset");
			System.out.println("2. Update Headset");
			System.out.println("3. Buy Headset");
			System.out.println("4. View Collection");
			System.out.println("5. Exit");
			System.out.print("Choose >> ");
			menu = in.nextInt(); in.nextLine();
			cls();
			switch (menu) {
			case 1:
				addHeadset();
				break;
			case 2:
				updateHeadset();
				System.out.println("Press enter to continue..."); in.nextLine();
				break;
			case 3:
				buyHeadset();
				System.out.println("Press enter to continue..."); in.nextLine();
				break;
			case 4:
				viewCollection();
				System.out.println("Press enter to continue..."); in.nextLine();
				break;
			}
		} while (menu != 5);
	}
	
	private boolean isUnique(String s) {
		for (Headset h : listHeadset) {
			if (s.equals(h.getName())) return false;
		}
		return true;
	}

	private void addHeadset() {
		String name, type;
		System.out.println("Add Headset");
		System.out.println("===========");
		System.out.println();
		do {
			System.out.print("Input headset name [5..15 | unique]: ");
			name = in.nextLine();
			if (!isUnique(name)) {
				System.out.println("'" + name + "' is already added, input another name.");
				name = "";
			}
		} while (name.length() < 5 || name.length() > 15);
		do {
			System.out.print("Input headset type [Headphone | IEM | OnEar]: ");
			type = in.nextLine();
		} while (!(type.equalsIgnoreCase("Headphone") || type.equalsIgnoreCase("IEM") || type.equalsIgnoreCase("OnEar")));
		System.out.println();
		listHeadset.add(new Headset(name, type));
		System.out.println("Headset successfully added!");
		System.out.println();
		System.out.println("Press enter to continue..."); in.nextLine();
	}

	private void updateHeadset() {
		System.out.println("Update Headset");
		System.out.println("===========");
		System.out.println();
		if (listHeadset.isEmpty()) {
			System.out.println("No headset added yet!");
		}
		else {
			int choose, total = listHeadset.size();
			String name, type;
			printTable(listHeadset);
			System.out.println();
			do {
				System.out.print("Select headset [1-" + total + "]: ");
				choose = in.nextInt(); in.nextLine();
			} while (choose < 1 || choose > total);
			do {
				System.out.print("Input headset name [5..15 | unique]: ");
				name = in.nextLine();
				if (!isUnique(name)) {
					System.out.println("'" + name + "' is already added, input another name.");
					name = "";
				}
			} while (name.length() < 5 || name.length() > 15);
			do {
				System.out.print("Input headset type [Headphone | IEM | OnEar]: ");
				type = in.nextLine();
			} while (!(type.equalsIgnoreCase("Headphone") || type.equalsIgnoreCase("IEM") || type.equalsIgnoreCase("OnEar")));
			listHeadset.get(choose-1).setName(name);
			listHeadset.get(choose-1).setType(type);
			listHeadset.get(choose-1).updatePrice(type);
			System.out.println();
			System.out.println("Headset successfully updated!");
			System.out.println();
			System.out.println("Press enter to continue..."); in.nextLine();
			cls();
			System.out.println("Update Headset");
			System.out.println("===========");
			System.out.println();
			printTable(listHeadset);
		}
	}

	private void buyHeadset() {
		System.out.println("Buy Headset");
		System.out.println("===========");
		System.out.println();
		if (listHeadset.isEmpty()) {
			System.out.println("No headset added yet!");
		}
		else {
			int choose, qty, discount, total = listHeadset.size();
			double price;
			discount = rand.nextInt(16) + 10;
			printTable(listHeadset);
			System.out.println();
			do {
				System.out.print("Select headset [1-" + total + "]: ");
				choose = in.nextInt(); in.nextLine();
			} while (choose < 1 || choose > total);
			do {
				System.out.print("Input quantity [min. 1]: ");
				qty = in.nextInt(); in.nextLine();
			} while (qty < 1);
			//agar headset di collection tidak berubah kalau ada update, bikin object baru
			Headset bought = new Headset(listHeadset.get(choose-1).getName(), listHeadset.get(choose-1).getType());
			System.out.println();
			System.out.println("You got " + discount + "% discount!");
			price = (double)(qty * bought.getPrice()) * ((double)(100-discount)/100);
			System.out.println("Total Price: " + (int)price);
			System.out.println();
			customer.addToCollection(bought);
			System.out.println("This headset has been added to your collection!");
			System.out.println();
		}
	}
	
	private void printTable(ArrayList<Headset> headsets) {
		int index = 0;
		System.out.println("+==================================+");
		System.out.println("| No - Headset name - Type         |");
		System.out.println("+==================================+");
		for (Headset h : headsets) {
			index++;
			System.out.println("| " + index + ". - " + h.getName() + " - " + h.getType() + " |");
		}
		System.out.println("+==================================+");
	}

	private void viewCollection() {
		System.out.println("View Collection");
		System.out.println("===============");
		System.out.println();
		if (listHeadset.isEmpty()) {
			System.out.println("No Headset added yet.");
		}
		else if (customer.getCollection().isEmpty()) {
			System.out.println("Your collection(s) are empty.");
		}
		else {
			System.out.println("Your Collection:");
			printTable(customer.getCollection());
		}
		System.out.println();
	}

	public static void main(String[] args) {
		new Main();
	}

}
