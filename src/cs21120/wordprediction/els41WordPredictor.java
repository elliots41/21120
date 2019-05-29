package cs21120.wordprediction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class els41WordPredictor implements WordPredictor {
	String choice = "empty";
	static ListMap list = new ListMap();
	static HashMap hash = new HashMap();
	static TreeMap tree = new TreeMap();
	
	@Override
	public void buildMap(String filename) {
		// TODO Auto-generated method stub
		
		Scanner scan= new Scanner(System.in); //scanner to get user input for implementation		
		String line = null;
		int keyNumber =0;
		
		try{
			FileReader fileReader = new FileReader(filename); 
			BufferedReader bufferedReader = new BufferedReader(fileReader);	//to read the dictionary file into the map 		
			System.out.println("input map implementation list hash or tree");
			choice = scan.nextLine(); //get implementation from user
			switch(choice){
			case "list":
				while((line = bufferedReader.readLine()) != null) { //as long as there is another line to read
					list.put(keyNumber, line); //add the word read to the map 
					keyNumber++;               //with an incrementing key
	            }
				break;
			case "hash":
				while((line = bufferedReader.readLine()) != null) {
					hash.put(keyNumber, line);
					keyNumber++;
	            }
				break;
			case "tree":
				while((line = bufferedReader.readLine()) != null) {
					tree.put(keyNumber, line);
					keyNumber++;
	            }
				break;
			default: //if the user doesnt input a valid option
				System.out.println("try again");
			}
			bufferedReader.close(); //the dictionary has been read so close reader
			
		} catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + 
                    filename + "'");                
            }
		 catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + filename + "'");                  
	        }

	}

	@Override
	public LinkedList<String> performWordPrediction(String word) {
		// TODO Auto-generated method stub
		// return a linked list of possibilities 
		//get the dictionary by user choice of implementation
		LinkedList predictionList = null;
		listPrediction newlist = new listPrediction();	
		hashPrediction newhash = new hashPrediction();
		treePrediction newtree = new treePrediction();
		
		word.toLowerCase();
		
		switch(choice){
		case "list":
			predictionList = newlist.performWordPrediction(word, list); //pass the test word and the dictionary 
			break;
		case "hash":
			predictionList = newhash.performWordPrediction(word, hash);
			break;
		case "tree":
			predictionList = newtree.performWordPrediction(word, tree);
			break;
		
	}
		return predictionList;
}
}

