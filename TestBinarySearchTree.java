/**
* C202 Program Assignment 5
* TestBinarySearchTree.java
* Purpose: Create 26 BST for each letter of the alphabet.
* Populate each of the BST with words from the dictionary in alphabetical order.
* Compare Oliver.txt with the random dictionary.
* Compute the number of words found and not found.
* Compute the average number of comparisons of words found and not found.
* @author Tyler Goodwin
* @version 1.0 11/11/16
*/
import java.util.*;
import java.io.*;
import java.lang.*;

public class TestBinarySearchTree {
    BinarySearchTree[] list = new BinarySearchTree[26];
    String word;
    //Remove special cases from all string words.
    public String remover(String l){
        String fixed = "";
        for(int i=0; i < l.length(); i++){//removes special characters
            if(l.charAt(i) > 96 && l.charAt(i) < 123){
                fixed += l.charAt(i);
            }//if
        }//for
        if(fixed == ""){
            fixed = "t";
        }//of
        return fixed;
    }
    //Populates each of the 26 BST with the random dictionary
    public void dictionary(){
        for(int i = 0; i < list.length; i++){//creates the 26 BST's
            list[i] = new BinarySearchTree<String>();
        }//for
        File d = new File("random_dictionary.txt");
        try{
            Scanner input = new Scanner(d);
            while(input.hasNext()){
                word = input.next();
                word = word.toLowerCase();
                int i = (word.charAt(0)-97);
                list[i].insert(word);
            }//while
        input.close();
        }//try
        catch(IOException e){}
    }//dictionary
    //Reads Oliver.txt and compares each word with that of the random dictionary
    public void oliver(){
        float found = 0;
        float nFound = 0;
        float cFound = 0;
        float cNFound = 0;
        try{
            File o = new File ("oliver.txt");
            Scanner input = new Scanner(o);
            while(input.hasNext()){
                word = input.next();
                word = word.toLowerCase();
                word = remover(word);
                int i = (word.charAt(0)-97);
                int[] counter = new int[1];
                counter[0] = 0;
                if (list[i].search(word, counter)){//searches for the word and adds to words found and comparisons
                    found++;
                    cFound += counter[0];
                }//if
                else{//if word is not found adds to words not found and comparisons not found
                    nFound++;
                    cNFound += counter[0];
                }//else
            }//while
            float avgFound = cFound / found;//finds average comparisons of words found
            float avgNFound = cNFound / nFound;//finds average comparisons of words not found
            System.out.print("Here is the number of words found: " + found + "\n" + 
            "Here is the number of words not found: " + nFound + "\n" + 
            "Here is the average number of comparisons for words found: " + avgFound + "\n" +
            "Here is the average number of comparisons for words not found: " + avgNFound);
            System.out.println("");
            input.close();
        }//try
        catch(IOException e){e.printStackTrace();}
    }//Oliver
    //Runs each of the methods
    public static void main(String[] args) {
        TestBinarySearchTree bob = new TestBinarySearchTree();
        bob.dictionary();
        bob.oliver();
    }
}
/**
* These are the two methods used to search through the trees
public boolean search(E e) {
    TreeNode<E> current = root; // Start from the root
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else // element matches current.element
        return true; // Element is found
    }
    return false;
  }
* This overloads the above method to implement the counter
public boolean search(E e, int[] count) {
    TreeNode<E> current = root; // Start from the root
    int i = 0;
    while (current != null) {
      i++;
      if (e.compareTo(current.element) < 0) {
        current = current.left;
        count[0] = i;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
        count[0] = i;
      }
      else{ // element matches current.element
        return true; // Element is found
      }
    }
    count[0] = i;
    return false;
}*/