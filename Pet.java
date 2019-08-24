//Pet.java

import java.io.*;
import java.util.Scanner;

public class Pet implements java.io.Serializable {
	public String petName; // holds the pet name
	public int petAge; // holds the pet age
	public String petType; // holds the type of pet

	public static void main(String[] args) {
		//instantiate a new pet
		Pet userpet = new Pet(); 
		userpet.printName(); // this line prints the existing pet name
		userpet.printAge(); // this line prints the initial pet age
		userpet.printTypeofPet(); // this wiil print the initial type of pet

		userpet.Save(); // Asks the user for saving their choices
		userpet.Birthday(); // Asks for the pet birthday
		userpet.Save(); // Asks the user again if they want to save	
	}

	public Pet() {
		// creates a brand new scanner for the program
		Scanner reader = new Scanner(System.in); 
		System.out.println("Do you want to load a saved pet? (y/n)");
		// if the user said "y", this try catch block tries to open a previously saved pet
		if ((reader.next()).equals("y")) { 
			try {
				FileInputStream fileIn = new FileInputStream("pet.ser"); 
				ObjectInputStream in = new ObjectInputStream(fileIn);
				Pet userpet = (Pet) in.readObject(); 
				in.close(); // close the input stream
				fileIn.close(); // close the input file
				this.petName = userpet.petName; // assigns the read name to the userpet
				this.petAge = userpet.petAge; // assigns the read age to the userpet
				this.petType = userpet.petType; // assigns the type of pet to userpet
			}
			// if something goes wrong, this will hopefully help us find the problem
			catch(IOException i) { 
				i.printStackTrace(); 
			}
			// let the user know we couldn't find the pet
			catch(ClassNotFoundException c) { 
				System.out.println("Pet class not found"); 
				c.printStackTrace(); // if something goes wrong, this will hopefully help us find the problem
			}
		} // end if

		// if the user said "n", we will ask info about their new pet
		else { 
			System.out.println("What is your pet's name?"); 
			this.petName = reader.next(); // assigns name to object
			System.out.println("What is your pet's age?"); 
			this.petAge = reader.nextInt(); // assigns age to object
			System.out.println("What type of pet do you have?");
			this.petType = reader.next(); // assigns type of pet to the object
		} // end else
	} // end Pet()
	
	// this function will ask about pet's birthday
	public void Birthday() {
		// creates a new input scanner
		Scanner reader = new Scanner(System.in); 
		System.out.println("Has your pet had a birthday since your last visit? (y/n)");
		// if the user said "y", increment the pet's age by 1
		if ((reader.next()).equals("y")) { 
			petAge += 1; 
			System.out.println("Happy Birthday " + petName + "!"); 
			this.printAge(); 
		} // end if
	} // end Birthday()

	// this serial function allows us to do the object serialization(save object's state to bytes) 
	public void Serialization() {
		// try block for new output filename "pet.ser"
		try { 
			FileOutputStream fileOut = new FileOutputStream("pet.ser"); 
			ObjectOutputStream out = new ObjectOutputStream(fileOut); 
			out.writeObject(this); // write the object to the output stream
			out.close(); // close the output stream
			fileOut.close(); // close the output file
			System.out.println("Serialized data is saved in pet.ser"); 
		}
		// if something goes wrong, this will hopefully help us find the problem 
		catch(IOException i) { 
			i.printStackTrace(); 
		}
	} // end Serialization()

	// function for saving the pet
	public void Save() { 
		System.out.println("Would you like to save your pet? (y/n)"); 
		Scanner reader = new Scanner(System.in);
		// if the user said "y", serialize with the serial function
		if ((reader.next()).equals("y")) { 
			this.Serialization(); 
		}
		else {
			System.out.println("GoodBye!"); 
			System.exit(0);
		}
	} // end Save()

	// constructor for set name, age and type of pet
	public Pet(String startname, int startAge, String typeofpet) { 
		setName(startname); 
		setAge(startAge); 
		setTypeofPet(typeofpet); 
	} // end constructor

	// function that let us print the age
	public void printAge(){ 
		System.out.println("Your pet's age is: " + petAge); 
	}

	// function that let us print the type of pet
	public void printTypeofPet(){ 
		System.out.println("Your pet's type is: " + petType); 
	}

	// function that let us print the name
	public void printName(){ 
		System.out.println("Your pet's name is: " + petName);
	}

	// setter for name
	public void setName(String newName) { 
		petName = newName;
	}

	//setter for age 
	public void setAge(int newAge) { 
		petAge = newAge;
	}

	//setter for type of pet
	public void setTypeofPet(String newtype) { 
		petType = newtype;
	}
	
} // end Pet

