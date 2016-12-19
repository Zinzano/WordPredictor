import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Fredrik Jonsson & Martin Tornkvist
 * Inlämningsuppgift språkteknologi
 */
public class FileManager {
	public FileManager() throws NumberFormatException, IOException {
		//onegramprinter();
		//moregramprinter();
		//getMessages("sms_corpus.txt");
		combineOneWords("words_lower_unique.txt", "w1_s.txt");

	}
	
	void getLatestWordsFromOnegram(String filename) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		TreeMap<String, Integer> allWords = new TreeMap<String, Integer>();
		FileWriter writer = new FileWriter("w1_s2.txt");
    	String line;
    	while((line = br.readLine()) !=null) {
    		String[] items = line.split("\t");
    		int CurrentFreq =Integer.parseInt(items[0]);
    		String currentWord = items[1];
    		allWords.put(currentWord, CurrentFreq);
    	}
    	
		for(Map.Entry<String, Integer> entry : allWords.entrySet()) {
			  String word = entry.getKey();
			  Integer prob = entry.getValue();
			  writer.write(prob + "\t" + word + "\n");
			}
    	writer.close();
    	br.close();
	}
	
	void combineOneWords(String filename1, String filename2) throws IOException{
		Map<String, String> allWords = new HashMap<>();
		BufferedReader file1 = new BufferedReader(new FileReader(filename1));
		BufferedReader file2 = new BufferedReader(new FileReader(filename2));
		String line;
		FileWriter writer = new FileWriter("combinedOneWords.txt");
		
		while((line = file1.readLine()) !=null) {
			allWords.put(line.split("\t")[0], line.split("\t")[0]);
    	}
		
		while((line = file2.readLine()) !=null) {
			allWords.put(line.split("\t")[1], line.split("\t")[1]);
    	}
		
		Iterator it = allWords.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry word = (Map.Entry)it.next();
			writer.write((String)word.getValue() + "\n");
		}
		it.remove();
		writer.close();
		file1.close();
		file2.close();
		
	}
	
	private void onegramprinter() throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new FileReader("w1_s2.txt"));
    	String line;
    	Double totFrequency = 0.0;
    	while((line = br.readLine()) !=null) {
    		String[] items = line.split("\t");
    		totFrequency = totFrequency + Double.parseDouble(items[0]);
    	}
    	br.close();
		FileWriter writer = new FileWriter("w1_s3.txt");
		BufferedReader br2 = new BufferedReader(new FileReader("w1_s2.txt"));
    	while((line = br2.readLine()) !=null) {
    		String[] items = line.split("\t");
    		writer.write(Math.log(Double.parseDouble(items[0])/totFrequency) + "\t" + items[1] +"\n");
    	}
    	writer.close();
    	br2.close();
	}
	
	void moregramprinter() throws NumberFormatException, IOException{
		for(int a=2;a<6;a++){
		Map<String, Double> frequencyCount = new HashMap<String, Double>();
    	BufferedReader br = new BufferedReader(new FileReader("w" + a +"_.txt"));
    	String line;
    	while((line = br.readLine()) !=null) {
    		String[] items = line.split("\t");
    		String preString="";
    		for(int i=1; i<items.length-1; i++){
    			preString += items[i];
    		}
    		if(frequencyCount.containsKey(preString)){
    			frequencyCount.put(preString, frequencyCount.get(preString) + Double.parseDouble(items[0]));
    		}else{
    			frequencyCount.put(preString, Double.parseDouble(items[0]));
    		}
    	}
    	br.close();
    	br = new BufferedReader(new FileReader("w" + a +"_.txt"));
		FileWriter writer = new FileWriter("w" + a +"_s.txt");
    	while((line = br.readLine()) !=null) {
    		String[] items = line.split("\t");
    		String preString="";
    		String preStringtoWrite =""; 
    		for(int k=1; k<items.length-1; k++){
    			preString += items[k];
    			preStringtoWrite = preStringtoWrite + items[k] +"\t";
    		}
    		writer.write(Double.toString(Math.log(Double.parseDouble(items[0])/frequencyCount.get(preString))) + "\t" + preStringtoWrite + items[items.length-1] + "\n");
    	}
    	writer.close();
    	br.close();
	}
	
	}
	
	private void getMessages(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		FileWriter writer = new FileWriter("onlyLetterMessages.txt");
		while ((line = br.readLine()) != null){
			String[] items = line.split("\t");
			String message="";
			for(String m: items[2].split(" ")){
				if(!m.matches("[a-zA-Z]+")){
					message="";
					break;
				}else{
					message = message + " " + m.toLowerCase();	
				} 
				  
			}
			items[2] = message;
			if(!message.equals("")){
				writer.write(items[0] + "\t" + items[1] + "\t" + items[2].trim() + "\t" + items[3]+ "\t" + items[4] + "\n");
			}
		}
		br.close();
		writer.close();
		br.close();
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		FileManager f = new FileManager();
	}
}
