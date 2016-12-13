import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FunktionalityTest {
	List<String> messages = new ArrayList<String>();
	WordPredictor wp = new WordPredictor();
	NgramSorter ns = new NgramSorter(1);
	
	public FunktionalityTest() throws IOException {
		//readSMSMessages("sms_corpus_letters_only.txt");
		//getMessages("sms_corpus.txt");
		//readEasySentences("nice_english_sentences.txt");
		readSingleWords("9000ord.txt");
		testT9(messages);
	}

	private void readSingleWords(String filename) throws IOException{
		//BufferedReader br = new BufferedReader(new FileReader(filename));
		System.out.println("Läser in enstaka ord");
		FileInputStream inputStream = null;
		Scanner sc = null;
		inputStream = new FileInputStream(filename);
	    sc = new Scanner(inputStream, "UTF-8");
	    while (sc.hasNextLine()) {
	        String line = sc.nextLine();
	        messages.add(line);
	        }
		System.out.println("Klar");
		sc.close();
	}
	
	private void readEasySentences(String filename) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader(filename));
		System.out.println("Läser in lätta meningar");
		FileInputStream inputStream = null;
		Scanner sc = null;
		inputStream = new FileInputStream(filename);
	    sc = new Scanner(inputStream, "UTF-8");
	    while (sc.hasNextLine()) {
	        String line = sc.nextLine();
	        messages.add(line);
	        }
		//reads first line
		//System.out.println("Läser in meningsdata");
		//br.readLine();
		//String line;
		//while ((line = br.readLine()) != null){
		//	messages.add(line);
		//}
		System.out.println("Klar");
		//br.close();
		sc.close();
		
	}

	// Kollar bara ord [^\\w] och små bokstäver 
	// kan ställa till det om det är många icke ord i meningarna
	private void testT9(List<String> messages) throws IOException {
		FileWriter writer = new FileWriter("testResults.txt");
		writer.write("tested word" + "\t" + "predicted word"+ "\n");
		System.out.println("Testar ord");
		int wrong = 0;
		int correct = 0;
		int tested = 0;
		for(String m:messages){
			String[] words = m.split("\\s+");
			String currentSentence = "";
			for(int i=0; i<words.length; i++){
				String wordToPredict = words[i].replaceAll("[^A-Za-z]", "").toLowerCase();
				if(!wordToPredict.equals("")){
					currentSentence = currentSentence + " " + wordToPredict;
					String number = getNumber(wordToPredict);
					List<String> T9word = wp.getWordFromNum(number);
					Object[][] listOfRankedWords = ns.getWordsByFrequency(T9word, currentSentence.split(" ", 2)[1]);
					String predictedWord = (String)listOfRankedWords[0][1];
					tested++;
				if(predictedWord.equals(wordToPredict)){
					correct++;
				}else{
					writer.write(wordToPredict + "\t" + predictedWord + "\n");
					wrong++;
					
				}
				}
			}
		}
		System.out.print("Tested: " +  tested + "\nWrong: " + wrong + "\nCorrect " +  correct + "\ncorrect: " + 100*correct/(double)tested + " %");
		writer.write("\nTested: " +  tested + "\nWrong: " + wrong + "\nCorrect " +  correct + "\ncorrect: " + 100*correct/(double)tested + " %");
		writer.close();
	}

	private String getNumber(String word) {
		String numWord="";
		for (char c: word.toCharArray()) {
            numWord += wp.alphabet.get(String.valueOf(c));
    }
		return numWord;
	}

	private void readSMSMessages(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		//reads first line
		System.out.println("Läser in sms-data");
		br.readLine();
		String line;
		while ((line = br.readLine()) != null){
			String[] items = line.split("\t");
			messages.add(items[2]);
		}
		System.out.println("Klar");
		br.close();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		FunktionalityTest ft = new FunktionalityTest();
	}

}
