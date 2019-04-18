/**** In this file, you are going to manipulate binary trees. 
 **** The main method is given to you. You will just have to follow instructions and uncomment code as prompted
 **** A set of helper methods are also provided to you: you may or may not elect to use them, it is fine. 
 **** There are 3 main TODOs in this file: TODO 6, TODO 7, and TODO 8.
 ****/

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Execute {

    /* TODO 6: 
     * Method readFamilyIntoArray: 
     * Takes a file name and reads this file with family information, 
     * creates and fills an array tree with family member information.
     * Note: the size of the array tree depends on the number of levels in the family tree
     *      not on the number of family members
     ****************************************************************************************/
    public static FamilyMember[] readFamilyIntoArray(String filename) throws FileNotFoundException, IOException {
        
        // Find out how many family levels there are in the file (i.e., the number of lines) 
        int lines; // = ...; COMPLETE CODE HERE
        // --> This gives us the size of the array
        int size; // = ...; COMPLETE CODE HERE 
        // Create an array of FamilyMember elements, with the correct size:
        FamilyMember[] Family = new FamilyMember[size];
        
        // Read the file called filename to gather information into the array
		FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);

        // YOUR CODE GOES HERE: COMPLETE HERE...
        
        textReader.close();
        
        // Returns the filled array
        return Family;
    }
        
    /* TODO 7: 
     * Method readFamilyIntoTree: 
     * Takes a file name and reads this file with family information, 
     * creates and fills a linked-list-based tree with family member information.
     * Note: Father-line nodes go to the left and Mother-line nodes go to the right
     ****************************************************************************************/
    public static BTree<FamilyMember> readFamilyIntoTree(String filename) throws FileNotFoundException, IOException {
        
        // Read the file to gather information into the array
		FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);

        // Create an empty binary tree of Family Members
        BTree<FamilyMember> Tree = new BTree<FamilyMember>();
        
        // YOUR CODE GOES HERE: COMPLETE HERE...
        
        textReader.close();
        
        // NOTE: Make sure that your tree has an updated size and height
        
        // Return the resulting filled tree
        return Tree;

    }
        
    /* TODO 8: 
     * Method ArrayToTree: 
     * Takes and array tree of FamilyMember instances, reads it, and builds the 
     * corresponding linked-list-based tree
     * Note: the array may not be full (some of its elements might be null): it represents a binary tree
     ****************************************************************************************/    
    public static BTree<FamilyMember> ArrayToTree(FamilyMember[] Family) {
        // Create an empty linked-list-based binary tree:
        BTree<FamilyMember> Tree = new BTree<FamilyMember>();
        
        // YOUR CODE GOES HERE: PLEASE COMPLETE HERE...
        
        // NOTE: Make sure that your tree has an updated size and height
        
        // Return the resulting filled linked-list-based binary tree
        return Tree;
    }
    
    
    /****************************************************************************************   
     * Main Method:
     ****************************************************************************************/    
	public static void main(String[] args) throws FileNotFoundException, IOException {
        String filename = args[0];

        // Creates an array tree based on what is read in the file called filename:
        FamilyMember[] Family = readFamilyIntoArray(filename);
        // Prints out the content of the array tree:
        for (int i=0; i<Family.length; i++) {
            if (Family[i] == null) {
                System.out.println("No entry here");
            } else {
                System.out.println(Family[i].toString());
            }
        } 
        System.out.println();
        System.out.println();
        
        // Creates a linked-list based tree from the array tree Family:
        BTree<FamilyMember> Tree = ArrayToTree(Family);
        // Prints out the content of the linked-list-based tree:
        Tree.print();
        System.out.println();
        
        System.out.println("Tree size = " + Tree.getSize());
        System.out.println("Tree height = " + Tree.getHeight());
        System.out.println();
        System.out.println();

        // Creates a linked-list-based tree directly from reading the file:
        BTree<FamilyMember> Tree2 = readFamilyIntoTree(filename);
        // Prints out the content of the linked-list-based tree:
        Tree2.print();
    }

    
    
    /************************************************************************************
     * HELPER METHODS: ******************************************************************
     ************************************************************************************/

    /* Method Directions: 
     * Given an integer, which represent the index of a piece of data in an array tree, 
     * this methode figures out what directions in the tree we should take to "plug" the node
     */
    public static String Directions(int i) {
        String directions = "";
        int index = i + 1; 
        while (index != 1) {
            if (index % 2 == 1) directions = "R" + directions;
            else directions = "L" + directions;
            index /= 2;    
        }
        System.out.println("Directions for member at index " + i + " is: " + directions);
        return directions;
    }
    
    /* Method processLine:
     * This method is given a String that is one element of the line in the text file for be read.
     * The element is of the following form: <String>-<String>,<int>,<String>
     * Example of such an element: John-Doe,3,LLR
     * It processes this element and produces an array of 4 strings: 
     * [first name, last name, number of siblings, location in the array where it should be stored]
     * In the case of the above example, we would produce the following array: [John, Doe, 3, 8]
     */
    public static String[] processLine(String element) {
        String[] result = new String[5];

        String[] member = element.split(",");
        String[] name = member[0].split("-");
        result[0] = name[0];
        result[1] = name[1];
        result[2] = member[1];
        result[3] = member[2];

        int place = 0;
        if (member[2].equals("0")) place = 0;
        else {
            place = 0;
            while (member[2].length() != 0) {
                if (member[2].charAt(0) == 'F') place = place*2 + 1;
                if (member[2].charAt(0) == 'M') place = place*2 + 2;
                member[2] = member[2].substring(1);   
            }
        }
        
        result[4] = "" + place;
        return result;
    }

    /* Method countLines: 
     * This method takes a file name as a parameter and 
     * returns the number of lines in this file (an int)
     */
    public static int countLines(String filename) throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);

        int counter = 0;
        // As long as there is something to read in the file...
        while (textReader.ready()) {
            // we increase our line counter
            counter++;
            // read the line and move to the next to check if there is something to read (the while condition)
            textReader.readLine();   
        }
        
        textReader.close();
        return counter;
    }


}