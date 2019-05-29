package cs21120.wordprediction;

import java.util.LinkedList;

/**
 * The interface to be implemented in this assignment.
 * 
 * @author      chz8
 */

public interface WordPredictor {

	/**
	 * Reads a given dictionary file and stores it in a Map.
	 *
	 * @param	filename	The file that contains the word list.
	 */	
	public void buildMap(String filename);

	/**
	 * Performs the word prediction task.
	 *
	 * @param	word	The word for which the prediction is made.
	 * @return	A linked list that contains all the predictions for the given word.
	 */
	public LinkedList<String> performWordPrediction(String word);
}
