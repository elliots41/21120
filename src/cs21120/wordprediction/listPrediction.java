package cs21120.wordprediction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class listPrediction {

	static LinkedList predictionList = new LinkedList();
	
	
	public LinkedList<String> performWordPrediction(String testWord, ListMap list) {
		// TODO Auto-generated method stub
		// return a linked list of possibilities 
		//get the dictionary by user choice of implementation
		
		if(list.containsValue(testWord)){//if the word itself is already in the dictionary add it to the predictions
			predictionList.add(testWord);
		}
		char[] wordChar = testWord.toCharArray();
		PermBool(testWord,list);
		//Permutations(wordChar, 0, wordChar.length, list);
		removeChars(testWord, list);
		addition(testWord, list);
		replacement(testWord, list);
		return predictionList;
	}
	
private static void replacement(String testWord, ListMap list){
	char[] alphabet={ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 's', 't', 'u' };
	int x =0;
	int alphabetCounter =0;
	while(alphabetCounter<=25){
		while(x<testWord.length()){
			String result = testWord.substring(0, x) + alphabet[alphabetCounter] + testWord.substring(x+1);
			if(!predictionList.contains(result)){ //avoids searching duplicates wont do anything if already in prediction list
				if(list.containsValue(result)){				
					predictionList.add(result);
				}
			}
			
			x++;			
		}
		alphabetCounter++;
		x=0;
	}
}
	
private static void addition(String testWord, ListMap list){
	char[] alphabet={ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 's', 't', 'u' };
	int x =0;
	int alphabetCounter =0;
	while(alphabetCounter<=25){
		while(x<=testWord.length()){
			String result = testWord.substring(0, x) + alphabet[alphabetCounter] + testWord.substring(x);
			if(!predictionList.contains(result)){
				if(list.containsValue(result)){				
					predictionList.add(result);
				}
			}
			x++;
		}
		alphabetCounter++;
		x=0;
	}
	
}

private static void removeChars(String testWord, ListMap list){
	int x =0;
	while(x<testWord.length()){
		String result = testWord.substring(0, x) + testWord.substring(x+1);
		x++;
		if(list.containsValue(result)){				
			predictionList.add(result);
		}
	}
	
}
public static boolean isAnagram(String firstWord, String secondWord) {
    char[] word1 = firstWord.replaceAll("[\\s]", "").toCharArray();
    char[] word2 = secondWord.replaceAll("[\\s]", "").toCharArray();
    Arrays.sort(word1);
    Arrays.sort(word2);
    return Arrays.equals(word1, word2);
}
private static void PermBool(String testWord, ListMap list){
	for (Object value : list.values()) {
		String q=value.toString();
		if(isAnagram(testWord,q)){
			predictionList.add(testWord.toString());
		}
	}
	
}
private static void Permutations(char[] testWord, int startIndex, int endIndex, ListMap list){
	
	if (startIndex == endIndex){
		{
			//check if in dictionary if so add it to predictions else ignore it as gibberish for now
			if(list.containsValue(testWord.toString())){				
				predictionList.add(testWord.toString());
			}
		}
	}
    else {
        for (int x = startIndex; x < endIndex; x++) {
            swap(testWord, startIndex, x);
            Permutations(testWord, startIndex + 1, endIndex, list);
            swap(testWord, startIndex, x);
        }
    }
}

private static void swap(char[] word, int i, int x) {
    char t=word[i];
    word[i]=word[x];
    word[x]=t;
}
}
