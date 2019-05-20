// George Tohme; PhonebookClient class:
import java.util.*;
import java.io.*;
public class PhonebookClient {
	// main 
	public static void main(String[] args) {
		Scanner console = new Scanner (System.in);
		intro(console);
		Phonebook phonebook = createPhonebook(console);
		int request;
		do {
			menu();
			breaker();
			request = console.nextInt();
			if (request == 1) { // add
				System.out.println("-------------------------");
				System.out.println("Adding a phonebook entry|");
				System.out.println("-------------------------");
				phonebook.add(console);
			} else if (request == 2) { // search
				System.out.println("--------------------------------");
				System.out.println("Searching for a phonebook entry|");
				System.out.println("--------------------------------");
				System.out.println("What is the last name of your desired entry?");
				searchAndPrint(console.next(), phonebook);
			} else if (request == 3) { // change
				System.out.println("---------------------------");
				System.out.println("Changing a phonebook entry|");
				System.out.println("---------------------------");
				phonebook.doChangeNumber(console);
			} else if (request == 4) { // display
				System.out.println("--------------------------------------");
				System.out.println("Printing all entries in the phonebook|");
				System.out.println("--------------------------------------");
				System.out.println(phonebook.sort());
			} else if (request != 5) {
				System.out.println("Sorry, not a valid menu choice.");
			}
			breaker();			
		} while (request != 5);
		System.out.println();
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - End - - - - - - - - - - - - - - - - - - - - - - - - - - -");
	}
	// intro
	public static void intro(Scanner console) {
		System.out.println();
		System.out.println("This program creates a phonebook by\nrequesting the number of desired entries");
		System.out.println("and then lets the user choose 1 of 5 commands:\nadd, search, change, display, or quit.");
		System.out.println();
	}
	// create phonebook
	public static Phonebook createPhonebook(Scanner console) {
		System.out.print("Enter the desired size of your phonenook (number of entries): ");
		int size = console.nextInt();
		Phonebook phonebook = new Phonebook(size);
		return phonebook;
	}
	// menu
	public static void menu() {
		System.out.println();
		System.out.println("-----");
		System.out.println("Menu |");
		System.out.println("--------------------------------------------------------");
		System.out.println("Press 1 to add an entry to your phonebook.              |");
		System.out.println("Press 2 to search for an entry in your phonebook.       |");
		System.out.println("Press 3 to change the phone number of an entry.         |");
		System.out.println("Press 4 to sort and display all your phonebook entries. |");
		System.out.println("Press 5 to quit.                                        |");
		System.out.println("--------------------------------------------------------");
	}
	// breaker 
	public static void breaker() {
		System.out.println("______________________________________________________________________________________________________");
	}
	// search and print
	public static void searchAndPrint(String lastName, Phonebook phonebook) {
		if (phonebook.searchNumMatches(lastName) == 0) {
			System.out.println("Sorry, no matches found.");
		} else {
			breaker();
			System.out.println("Here are the entries that matched your search with their index in your phonebook:");
			phonebook.searchAndPrint(lastName);
		}
	}
}