// George Tohme
// This class creates a Phonobook object
import java.util.*;
import java.io.*;
public class Phonebook {
	// Fields
	PhonebookEntry[] phonebook;
	int currentEntry;
	int size;
	
	// Constructors
	Phonebook (int size) {
		this.size = size;
		currentEntry = 0;
		phonebook = new PhonebookEntry[size];	
	}
	// setters
	public void setPhonebook (PhonebookEntry phonebookEntry, int i) {
		this.phonebook[i] = phonebookEntry;
	}
	
	// getters 
	public PhonebookEntry getPhoneboookEntry(int i) {
		return phonebook[i];
	}
	public int getSize() {
		return this.size;
	}
	// Instance Methods:
	public boolean isFull() {
		for (int i = 0; i < this.getSize(); i++) {
			if (phonebook[i] == null) {
				return false;
			}
		}
		return true; 
	}
	public boolean add (Scanner console) {
		if (isFull() || currentEntry >= phonebook.length) {
			System.out.println("Sorry, the phonebook is full, no entry can be added.");
			return false;
		}
		// prompt user to enter phonebook entry:
		System.out.println("Enter first name, then last name, and finally the number\nseperated by a single space.(Example: Toni Tohme 03385730): ");
		phonebook[currentEntry] = new PhonebookEntry(console.next(), console.next(), console.next());
		currentEntry++;
		System.out.println("The entry has been added.");
		return true;
	}
	public void searchAndPrint(String lastName) {
		for (int i = 0; i < phonebook.length; i++) { //loop can run till currentEntry but let's keep it more general to avoid possible future bugs incase we add inconsistant code later.
			if (phonebook[i]!= null && phonebook[i].getLastName().equals(lastName)){
				System.out.println(i+ ": "+phonebook[i]);
			}
		}
	}
	public int searchNumMatches (String lastName) {
		int counter = 0;
		for (int i = 0; i < phonebook.length; i++) { 
			if (phonebook[i]!= null && phonebook[i].getLastName().equals(lastName)){
				counter++;
			}
		}
		return counter;
	}
	public int getIndex(String lastName) {
		for (int i = 0; i < phonebook.length; i++) { 
			if (phonebook[i]!= null && phonebook[i].getLastName().equals(lastName)){
				return i;
			}
		}
		return -1;
	}
	public void changeNumber (int index, String newNum) {
		if (index < 0 || index > phonebook.length || phonebook[index] == null){
			System.out.println("Invalid index");
		} else {
			phonebook[index].changeNumber(newNum);
		}
	}
	public void doChangeNumber (Scanner console) {
		System.out.println("What is the last name of the person whose number you want to change?");
		String lName = console.next();
		// putting the previous method to use
		int matches = searchNumMatches(lName);
		if (matches == 0) {
			System.out.println("Sorry, no entries matched your search.");
		} else if (matches == 1) {
			System.out.println("Exactly one entry matched.");
			int i = getIndex(lName);
			System.out.println(i+ " " +phonebook[i]);
			System.out.println("What is the new phone number?");
			phonebook[i].changeNumber(console.next());
			System.out.println("Number changed.");
		} else {
			System.out.println("Here are the matching entries:");
			searchAndPrint(lName);
			System.out.println("Which entry would you like to change?");
			int i = console.nextInt();		
			System.out.println("What is the new phone number?");
			this.changeNumber(i, console.next());
			System.out.println("Number changed.");
		}
	}
	
	// sorting method
	public Phonebook sort() {
		Phonebook sorted = new Phonebook(this.getSize()); // created a new phonebook object with its PhonebookEntry[] having same size as this PhonebookEntry[].
		int index = 0;
		int iLeast = 0;
		// loop all this.phonebook array, taking out the leastPhonebookEntry, and place it in the sorted array
		for (int j = 0; j < currentEntry; j++) {
			PhonebookEntry leastPhonebookEntry = new PhonebookEntry ("zzzz", "zzzz", "99999999"); // setting a max so that the comparison would surely be less
			for (int i = 0; i < currentEntry; i++) {
				if (this.phonebook[i].compareTo(leastPhonebookEntry) == -1) {
					iLeast = i;
					leastPhonebookEntry = this.phonebook[i];
				}
			}
			sorted.setPhonebook(leastPhonebookEntry, index);
			phonebook[iLeast] = new PhonebookEntry ("zzzz", "zzzz", "99999999");
			index++;
		}	
		for (int i = 0; i < size; i++) {
			this.phonebook[i] = sorted.getPhoneboookEntry(i);
		}
		return sorted;
	}
	
	// toString method
	public String toString() {
		String phonebookStr = "";
		for (int i = 0; i < phonebook.length; i++) {
			if(phonebook[i]!= null) {
				phonebookStr = phonebookStr + "\n" +i+ ". " + phonebook[i];
			}
		}
		return phonebookStr;
	}
}
