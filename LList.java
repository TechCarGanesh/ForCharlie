/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socialnet;

/*
Name: Ganesh Kumar
Team Name: TheBugatti
Instructor: Professor Mirsaeid Abolghasemi
Date: 01/26/2025
Project Information: Implements a reverse method to reverse the order of elements
in a linked list in one pass by altering the links and cycle method to move the
first element in the list to the last position.
*/
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ganesh Kumar
 */

import java.io.*;

/** A linked implementation of the ADT List.
 * 
 * This code is from Chapter 14 of 
 * Data Structures and Abstractions with Java 4/e
 *      @author Frank M. Carrano 
 * 
 * Modifications were made by Charles Hoot:
 * The toString method is overwritten to give a nice display of the items in
 * the list in this format { <1> <2> <3> <4> }
 * 
 * An alternate display method has been created to print the list one item 
 * to a line along with the index
 * 
 * Stubs were added for the methods needed to complete Lab 13
 * 
  * @version 4.0
 */

// Implementation of a LList abstract data type using ListInterface.
// ListInterface methods implemented are clear, initializeDataFields, add,
// remove, contains, getEntry, and replace.
class LList<T> implements ListInterface<T> {

    private Node firstNode;  // Reference to first node of chain
    private int numberOfEntries;

    public LList() {
        initializeDataFields();
    } // end default constructor

    public void clear() 
    {
        initializeDataFields();

    } // end clear
    
    // Initialize the class's data fields to indicate an empty list.
    private void initializeDataFields() {
        firstNode = null;
        numberOfEntries = 0;
    }

    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        } else // Add to end of nonempty list
        {
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode); // Make last node reference new node
        } // end if

        numberOfEntries++;
    } // end add

    public void add(int newPosition, T newEntry) {
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);
            if (newPosition == 1) // Case 1
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } else // Case 2: List is not empty
            { // and newPosition > 1
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            } // end if
            numberOfEntries++;
        } else {
             throw new IndexOutOfBoundsException("Illegal position given to add operation.");
        }
    } // end add

    public T remove(int givenPosition) {
        T result = null; // Return value
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            if (givenPosition == 1) // Case 1: remove first entry
            {
                result = firstNode.getData(); // Save entry to be removed
                firstNode = firstNode.getNextNode(); // Remove entry
            } else // Case 2: Not first entry
            {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData(); // Save entry to be removed
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);  // Remove entry
            } // end if
            numberOfEntries--;              // Update count
            return result;
        } 
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");

    } // end remove

    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNextNode();
            }
        } // end while
        return found;
    } // end contains

    public T getEntry(int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            return  getNodeAt(givenPosition).getData();
        } else 
             throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    } // end getEntry

    public T replace(int givenPosition, T newEntry) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            Node desiredNode = getNodeAt(givenPosition);
            T originalEntry = desiredNode.getData();
            desiredNode.setData(newEntry);
            return originalEntry;
        }
       else
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");

    } // end replace

    public int getLength() {
        return numberOfEntries;
    }

    public boolean isEmpty() {
        boolean result;
        if (numberOfEntries == 0) // Or getLength() == 0
        {
            assert firstNode == null;
            result = true;
        } else {
            assert firstNode != null;
            result = false;
        } // end if
        return result;
    } // end isEmpty

    public T[] toArray() {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        } // end while
        return result;
    } // end toArray

    // Return a reference to the node at a given position.
    // Precondition: List is not empty;
    //              1 <= givenPostion <= numberOfEntries
    private Node getNodeAt(int givenPosition) {
        assert (firstNode != null)
                && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;
        // Traverse the chain to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++) {
            currentNode = currentNode.getNextNode();
        }
        assert currentNode != null;
        return currentNode;
    } // end getNodeAt

    private class Node {

        private T data; // entry in bag
        private Node next; // link to next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        private T getData() {
            return data;
        } // end getData

        private void setData(T newData) {
            data = newData;
        } // end setData

        private Node getNextNode() {
            return next;
        } // end getNextNode

        private void setNextNode(Node nextNode) {
            next = nextNode;
        } // end setNextNode
    } // end Node

    /** Build a string representation of the list.
     *
     * @return A string showing the state of the list.
     */
    public String toString() {
        String result = "{ ";
        Node currentNode = firstNode;
        while (currentNode != null) {

            result = result + "<" + currentNode.data + "> ";
            currentNode = currentNode.next;

        }
        result = result + "}";

        return result;
    }

    /** Display the list with indices one to a line
     *  This will correctly display an infinite list,
     *  whereas the toString() method will never return.
     * 
     */
    public void display() {
        int index = 1;
        Node currentNode = firstNode;

        while (currentNode != null) {
            System.out.println(index + ":" + currentNode.getData());

            currentNode = currentNode.getNextNode();
            index++;
        }

    } // end display

    
   /** Check to see if two lists are the same.  
    * @param aList Another linked list to check this list against.
    * @return True if all the items in this list and the other list are equals.
    */
    public boolean equals(LList<T> aList) {
        boolean isEqual = false; // result of comparison of lists

        Node currOne = firstNode;
        Node currTwo = aList.firstNode;
        int counter;

        if (numberOfEntries == aList.numberOfEntries) {
            // Lists have equal lengths, so traverse both and compare items as you go:
            // (NOTE: loop is skipped if lists are empty)

            while ((currOne != null) && (currOne.getData()).equals(currTwo.getData())) {
                currOne = currOne.getNextNode();
                currTwo = currTwo.getNextNode();
            } // end while

            // If we made it to the end, the lists are equal
            isEqual = (currOne == null);
        }

        return isEqual;
    } // end equals

    
        
    /*********************************************************************
     * 
     * METHODS TO BE COMPLETED
     * 
     ***********************************************************************/
     

    /** Reverse the order of items in a list.
     */
    public void reverse() {
        Node first = firstNode;
        Node second = null;
        Node third = null;
        // If the list is empty, return.
        if(numberOfEntries <= 1) {
            return;
        }
        // Reverse the first two nodes.
        second = first.next;
        third = second.next;
        first.next = null;
        second.next = first;
        firstNode = second;
        // If the size of the list 2, return.
        if(numberOfEntries == 2) {
            return;
        }
        // Reverse the rest of the list.
        while(third != null) {
            first = second;
            second = third;
            third = third.next;
            second.next = first;
            firstNode = second;
        }       

    }

    /** Cycle the first item to the end of the list.
     */
    // Start in the initial state.
    // Create a variable that refers to the second node in the list.
    // Find and then create a variable that refers to the last node in the list.
    public void cycle() {
        if(numberOfEntries == 0) {
            return;
        }
        Node secondNode = firstNode.next;
        Node lastNode;
        // If the list has 1 entry, return.
        if(numberOfEntries == 1) {
            return;
        }
        // If the list has 2 entries, make the lastNode first and firstNode.next
        // equal to null.
        if(numberOfEntries == 2) {
            secondNode.next = firstNode;
            firstNode.next = null;
            firstNode = secondNode;
            return;
        }
        // When numberOfEntries > 2:
        lastNode = getNodeAt(numberOfEntries);
        firstNode.next = null;
        lastNode.next = firstNode;
        while(secondNode != lastNode) {
            firstNode = secondNode;
            secondNode = secondNode.next;
            firstNode.next = lastNode;
        }
    }
}
