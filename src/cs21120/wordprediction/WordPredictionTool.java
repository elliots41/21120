package cs21120.wordprediction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * The framework that will be used for testing.
 * 
 * @author      chz8
 */

public class WordPredictionTool {

	// Change the following four variables to modify default parameters!
	
	/**
	 * The name of the file used as dictionary.
	 */
	private String dictionaryFile = "dictionary.txt";
	
	/**
	 * The name of the file containing a list of words used as input for the word prediction task.
	 */
	private String wordFile = "words1.txt";
	
	/**
	 * A single word used as input for the word prediction task.
	 */
	private String word = "test";
	
	/**
	 * Variable indicating the Map implementation used.
	 */
	private String method = "hashing";

	/**
	 * The word predictor. You should instantiate an object of your own class in the constructor.
	 */
	private WordPredictor predictor = null;

	/**
	 * Variables used for measuring running time.
	 */
	private long startTime, endTime = 0;
	private double elapsedTime = 0.0;

	/**
	 * Constructor that uses the command line parameters and creates a new WordPredictor.
	 * 
	 * Calls the function to read command line parameters if there are any.
	 * 
	 * @param	args	The command line parameters.
	 */
	public WordPredictionTool(String[] args) {
		if (args.length > 0)
			readCommandLineParameters(args);
		else
			System.out.println("No parameters defined. Using default values.");

		/**
		 * TODO: Initialise your WordPredictor object(s) here!
		 */

		predictor = new els41WordPredictor();
	}

	/**
	 * Processes the command line Parameters.
	 * 
	 * @param	args	The command line parameters.
	 */
	public void readCommandLineParameters(String[] args){
		try{
			for (int i = 0; i < args.length; i=i+2) {
				switch (args[i].charAt(1)) {
				case 'd':
					dictionaryFile = args[i+1];
					break;
				case 'w':
					word = args[i+1].toLowerCase();
					break;
				case 'l':
					wordFile = args[i+1];
					break;
				case 'm':
					if ( (args[i+1].contentEquals("hashing")) || (args[i+1].contentEquals("tree")) || (args[i+1].contentEquals("list"))) 
						method = args[i+1];
					else
						throw new IllegalArgumentException("Not a valid method: "+args[i+1]);
					break;
				default:
					throw new IllegalArgumentException("Not a valid argument: "+args[i]);
				}
			}

			if (( dictionaryFile == "" ) || ( (word == "") && (wordFile =="")) || (method =="")){
				throw new IllegalArgumentException("Missing parameters.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			printHelpText();
		}
	}

	/**
	 * Prints help text to illustrate the use of command line parameters. 
	 */
	public static void printHelpText(){
		System.out.println();
		System.out.println("Use the following command line paramters to overwrite the default values:");
		System.out.println("(a list of words (-l) is prioritised over a single word (-w))");
		System.out.println("-d filename	name of the file used as dictionary");
		System.out.println("-w word		a single word to be used for prediction");
		System.out.println("-l wordlist	a file containing a list of words used for prediction");
		System.out.println("-m method		either hashing, tree or list");
		System.exit(1);
	}

	/**
	 * Executes the tasks: Builds the Map and performs word prediction.
	 * 
	 * Measures the time taken for the two steps.
	 */
	public void run(){
		/*
		 * Read the word list and build the Map
		 */
		System.out.println("Building the Map ("+method+").");
		startTime = System.nanoTime(); //alternative: System.currentTimeMillis();
		predictor.buildMap(dictionaryFile);
		endTime = System.nanoTime();
		elapsedTime = (endTime-startTime)/1000000; //alternative: elapsedTime = (end-start);
		System.out.println("Building the Map took "+elapsedTime+"ms.");


		/**
		 * Perform predictions for the given word or wordlist
		 */
		LinkedList<String> predictions = null;

		if ( wordFile != "" ){
			/*
			 * List of words.
			 */
			try {
				/**
				 * Read words from file and store them in a LinkedList.
				 * Prevents that time for accessing the file is included in the
				 * measurements.
				 */
				System.out.println("Reading "+wordFile+".");
				FileReader fileReader = new FileReader(wordFile);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String line;
				LinkedList<String> wordList = new LinkedList<String>();
				while ((line = bufferedReader.readLine()) != null) {
					wordList.add(line.toLowerCase());
				}
				fileReader.close();

				/**
				 * Iterate through LinkedList and perform predictions.
				 */
				System.out.println("Performing predictions for "+wordList.size()+" words in "+wordFile+".");
				startTime = System.nanoTime();
				for (String currentWord : wordList) {
					predictions = predictor.performWordPrediction(currentWord);
				}
				endTime = System.nanoTime();
				elapsedTime = (endTime-startTime)/(double)1000000;
				System.out.println("Word prediction took "+elapsedTime+"ms.");
			} catch (IOException e) {
				/*
				 *  print errors and exit if anything goes wrong
				 */
				e.printStackTrace();
				System.exit(1);
			}	
		}
		else{
			/*
			 * single word
			 */
			System.out.println("Performing predictions for word '"+word+"'.");
			predictions = predictor.performWordPrediction(word.toLowerCase());
			if ((predictions == null) || (predictions.isEmpty()))
				System.out.println("No predictions available.");
			else
				System.out.println("List of potential words for input '"+word+"': "+predictions);
		}
	}

	/**
	 * The main method.
	 * 
	 * @param args	The command line parameters.
	 */
	public static void main(String[] args) {
		WordPredictionTool testPrediction = new WordPredictionTool(args);
		testPrediction.run();
	}

}
