/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socialnet;
import java.util.Iterator;

/** 
   A driver that demonstrates the class LinkedDictionary.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class Driver
{
    
        public static void main(String[] args) {
		testSocialNetwork();
		System.out.println("\n\nDone.");

        }
	
	public static void testSocialNetwork()
	{
            // Create a dictionary 
            DictionaryInterface<String, UserProfile> profiles = new LinkedDictionary<>();
            ListInterface<String> emailList = new LList<>();
            String john = "John";
            String jane = "Jane";
            String tom = "Tom";

            System.out.println("Create a dictionary:\n");
            System.out.println("Initial dictionary should be empty; isEmpty() returns " + 
		                   profiles.isEmpty());
            SocialNetwork network = new SocialNetwork();
            UserProfile p1 = new UserProfile(john, "Doe", "john@example.com", "1234567890");
            UserProfile p2 = new UserProfile(jane, "Smith", "jane@example.com", "0987654321");

        
            network.add(p1.getEmail(), p1);
            network.add(p2.getEmail(), p2);

        
            p1.addFriend(p2.getEmail());
            p2.addFriend(p1.getEmail());

        
            network.displayProfiles();
        
            p1.modifyProfile(john, "Doe", "john@example.com", "1112223333");
        
            network.remove("john@example.com");
            network.displayProfiles();
		
            // Test add
            System.out.println("\n\nTesting add():\n");
            UserProfile p3 = new UserProfile("Tom", "Murphy", "tom@example.com", "0987654321");
            profiles.add("tom@example.com",   p3);
        
            emailList = profiles.getKeyIterator();


            System.out.println(emailList.getLength() + " (should be 11) items in dictionary, as follows:\n");
            display(emailList);
		
            // Test getValue
            System.out.println("\n\nTesting getValue():\n");
            System.out.println("\nJane:   " + emailList.getValue("Jane")   + " should be 555-5678");
            System.out.println("\nJohn:    " + emailList.getValue("John")    + " should be 555-7890");
            System.out.println("\nTom:    " + emailList.getValue("tom")    + " should be 555-5555");

	
// Test contains
		System.out.println("\n\n\nTesting contains():\n");

		if ( emailList.contains(john) )
			System.out.println("\nJohn is in network - OK");
		else 
			System.out.println("Error in contains()");
		
		if ( emailList.contains(jane) )
			System.out.println("\nJane is in network - OK");
		else 
			System.out.println("Error in contains()");
		
		if ( emailList.contains(tom) )
			System.out.println("\nTom is in network - OK");
		else 
			System.out.println("Error in contains()");
		
		if ( emailList.contains(tom) )
			System.out.println("\nTom is in dictionary - OK");
		else 
			System.out.println("Error in contains()");

		if (!emailList.contains(""))
			System.out.println("\nBo is not in dictionary - OK");
		else 
			System.out.println("Error in contains()");

                // Remove first item
		System.out.println("\n\n\nRemoving first item John - John's number is " +
		                   emailList.remove(john) + " should be 555-7894");

		System.out.println("\n" + emailList.getSize() + 
		              " (should be 10) items in dictionary, as follows:\n");
		display(emailList);
		

	} // testDictionary
	
	public static void display(DictionaryInterface<String, String> dictionary)
	{
            KeyIterator<String> keyIterator   = dictionary.getKeyIterator();
            keyIterator<String> valueIterator = dictionary.getValueIterator();
		
            while (keyIterator.hasNext() && valueIterator.hasNext())
		System.out.println(keyIterator.next() + " : " + valueIterator.next());
		System.out.println();
	} // end display
}  // end Driver

