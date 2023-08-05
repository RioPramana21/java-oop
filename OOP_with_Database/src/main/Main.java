package main;

import java.util.ArrayList;
import java.util.Scanner;

import classes.*;
import db.QueryDB;

public class Main {

	ArrayList<Furniture> furnitures = new ArrayList<>();
	ArrayList<Transaction> transactions = new ArrayList<>();
	Scanner in = new Scanner(System.in);
	QueryDB q = new QueryDB();
	
	private void cls() {
		for (int i = 0; i < 21; i++) { //biar rapi saja
			System.out.println();
		}
	}
	
	public Main() {
		mainMenu();
	}
	
	private void mainMenu() {
		int menu = 0;
		do {
			System.out.println("FurniStore");
			System.out.println("==========");
			System.out.println("1. Buy Furniture");
			System.out.println("2. View Transaction");
			System.out.println("3. Cancel Transaction");
			System.out.println("4. Exit");
			System.out.print("Input[1-4]: ");
			menu = in.nextInt(); in.nextLine();
			cls();
			switch (menu) {
			case 1:
				buyFurniture();
				break;
			case 2:
				viewTransaction();
				System.out.println("Press Enter to Continue"); in.nextLine();
				break;
			case 3:
				cancelTransaction();
				break;
			}
		} while (menu != 4);
		System.out.println("Thank You For Using this Program");
	}
	
	private boolean isValidEmail(String e) {
		if (e.contains("@") && e.endsWith(".com")) return true;
		return false;
	}

	private void buyFurniture() {
		String email, type;
		int qty;
		do {
			System.out.print("Input your Email [Contains '@' | Ends with '.com']: ");
			email = in.nextLine();
		} while (!isValidEmail(email));
		do {
			System.out.print("Input Furniture Quantity to Buy [1-15](Inclusive): ");
			qty = in.nextInt(); in.nextLine();
		} while (qty < 1 || qty > 15);
		do {
			System.out.print("Input Furniture Type [Bed | Couch](Case Sensitive): ");
			type = in.nextLine();
		} while (!(type.equals("Bed") || type.equals("Couch")));
		//Display table
		int count = 0;
		cls();
		furnitures = q.getFurnituresData();
		if (type.equals("Bed")) count = printBedTable();
		else if (type.equals("Couch")) count = printCouchTable();
		System.out.println();
		//Input index
		int index;
		do {
			System.out.print("Input Index to Select: ");
			index = in.nextInt(); in.nextLine();
		} while (index < 1 || index > count);
		//transaction details
		Furniture temp = furnitures.get(index-1);
		if (type.equals("Couch")) temp = furnitures.get(index+5-1);
		printTransactionDetail(temp, qty);
		q.insertTransaction(temp.getId(), email, qty);
	}

	private void printTransactionDetail(Furniture f, int qty) {
		int totalPrice = 0;
		Bed b = null;
		Couch c = null;
		System.out.println("Transaction Detail");
		System.out.println("==================");
		System.out.println("Furniture ID: " + f.getId());
		System.out.println("Furniture Name: " + f.getName());
		System.out.println("Furniture Size: " + f.getSize());
		System.out.println("Furniture Quality: " + f.getQuality());
		if (f instanceof Bed) {
			b = (Bed) f;
			String foldable = (b.isFoldable()) ? "Yes" : "No";
			totalPrice = b.getTotalPrice();
			System.out.println("Furniture Foldable: " + foldable);
		}
		else if (f instanceof Couch) {
			c = (Couch) f;
			String hasArm = (c.isHasArmRest()) ? "Yes" : "No";
			String hasLeg = (c.isHasLegExtension()) ? "Yes" : "No";
			totalPrice = c.getTotalPrice();
			System.out.println("Furniture Has Arm Extension: " + hasArm);
			System.out.println("Furniture Has Leg Extension: " + hasLeg);
		}
		System.out.println("Quantity: " + qty);
		System.out.println("Subtotal: " + totalPrice);
		System.out.println("Total Price: " + totalPrice * qty);
	}

	private int printBedTable() {
		System.out.println("=================================");
		System.out.println("|No. |ID   |Name      |Size |Quality |Foldable|");
		System.out.println("=================================");
		int i = 0;
		for (Furniture f : furnitures) {
			i++;
			if (f instanceof Bed) {
				System.out.printf("%d.  |", i);
				((Bed)f).printData();
			}
			else return i-1;
		}
		System.out.println("=================================");
		return i;
	}

	private int printCouchTable() {
		System.out.println("===================================================");
		System.out.println("|No. |ID   |Name      |Size |Quality |HasArmExtension|HasLegExtension|");
		System.out.println("===================================================");
		int i = 0;
		for (Furniture f : furnitures) {
			i++;
			if (f instanceof Couch) {
				System.out.printf("%d.  |", i);
				((Couch)f).printData();
			}
			else {
				i--;
				continue;
			}
		}
		System.out.println("=================================");
		return i;
	}

	private void viewTransaction() {
		transactions = q.getTransactionsData();
		System.out.println("==================");
		System.out.println("| Transaction ID | User Email | Quantity | FurnitureID | Furniture Name |");
		System.out.println("==================");
		if (transactions.isEmpty()) System.out.println("No Transaction Data Available");
		else {
			for (Transaction t : transactions) {
				t.printTransactionData();
				System.out.println("| " + q.getFurnitureNameById(t.getFurnitureId()) +" |");
			}
		}
		System.out.println("==================");
	}
	
	private void cancelTransaction() {
		int delete;
		viewTransaction();
		if (!transactions.isEmpty()) {
			int size = transactions.size();
			do {
				System.out.print("Input Transaction ID to Cancelled: ");
				delete = in.nextInt(); in.nextLine();
			} while (delete < 1 || delete > size);
			q.deleteTransaction(delete);
			System.out.println("Transaction Cancelled");
		}
		System.out.println("Press enter to Continue..."); in.nextLine();
	}

	public static void main(String[] args) {
		new Main();
	}

}
