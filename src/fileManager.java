import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class fileManager {

	public fileManager() throws NumberFormatException, IOException {
		onegramprinter();
		moregramprinter();
	}
	private void onegramprinter() throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new FileReader("w1_.txt"));
    	String line;
    	Double totFrequency = 0.0;
    	while((line = br.readLine()) !=null) {
    		String[] items = line.split("\t");
    		totFrequency = totFrequency + Double.parseDouble(items[0]);
    	}
    	br.close();
    	br = new BufferedReader(new FileReader("w1_.txt"));
		FileWriter writer = new FileWriter("w1_s.txt");
    	while((line = br.readLine()) !=null) {
    		String[] items = line.split("\t");
    		writer.write(Math.log(Double.parseDouble(items[0])/totFrequency) + "\t" + items[1] +"\n");
    	}
    	writer.close();
    	br.close();
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
	public static void main(String[] args) throws IOException {
		fileManager f = new fileManager();
	}
}
