// George Tohme
// This class creates a PhonobookEntry object

public class PhonebookEntry {
	// Fields
	private String firstName;
	private String lastName;
	private String number;
	
	// Constructor 
	public PhonebookEntry(String fName, String lName, String num) {
		firstName = fName;
		lastName = lName;
		number = num;
	}
	
	// getters
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getNumber() {
		return number;
	}
	// setters
	public void changeNumber(String newNumber) {
		number = newNumber;
	}
	public void changeLastName(String newLastName) {
		lastName = newLastName;
	}
	
	// Other Instance Methods
	public boolean hasSameLastName(String lastName) {
		return this.lastName.equals(lastName);
	}
	public int compareTo(PhonebookEntry p) {
		// same last name and first name
		if (firstName.equals(p.firstName) && lastName.equals(p.lastName)) {
			return 0;
		} else if (lastName.equals(p.lastName)) { // same last name
			// Alphabetically search first names:
			int min = Math.min(firstName.length(), p.firstName.length());
			String firstN = firstName.substring(0,min+1);
			String pFirstN = p.firstName.substring(0,min+1);
			// substrings are same, then the longer one is greater.
			if (firstN.equals(pFirstN)) {
				if (firstName.length() > p.firstName.length()) {
					return 1;
				} else {
					return -1;
				}
			} else {
				for (int i = 0; i < min; i++) {
					if (firstN.charAt(i) < pFirstN.charAt(i)) {
						return -1;
					} else if (pFirstN.charAt(i) < firstN.charAt(i)) {
						return 1;
					} 
				}
				return 0;
			}
		} else {
			// different last names
			// Alphabetically search last names:
			int min = Math.min(p.lastName.length(), lastName.length());
			String lastN = lastName.substring(0,min);
			String pLastN = p.lastName.substring(0,min);
			// substrings are same, then the longer one is greater.
			if (lastN.equals(pLastN)) {
				if (lastName.length() > p.lastName.length()) {
					return 1;
				} else {
					return -1;
				}
			}
			for (int i = 0; i < min; i++) {
				if (lastN.charAt(i) < pLastN.charAt(i)) {
					return -1;
				} else if (pLastN.charAt(i) < lastN.charAt(i)) {
					return 1;
				} 
			}
			return 0;
		}
	}
	
	// toString Method
	public String toString() {
		String numberString = number.substring(0,2) +"-"+ number.substring(2);
		return firstName+ " " +lastName+ ": " + numberString;
	}
}