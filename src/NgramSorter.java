import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Fredrik Jonsson & Martin Tornkvist
 * Inlämningsuppgift språkteknologi
 */
public class NgramSorter {
    Map<List<String>, String> wordFrequencyMap = new HashMap<>();
    // Ställer in max n-gram som man vill använda. Max är 5
	int nGramVersion;
	
	public NgramSorter(int version) throws IOException {
        nGramVersion = version;
        readFiles();
	}

	private void readFiles() throws IOException {
        for (int i = 1; i <= nGramVersion; i++){
        	System.out.println("Läser in n-gram fil " + i);
        	String filename = "COCA/w" + i + "_s.txt";
        	BufferedReader br = new BufferedReader(new FileReader(filename));
        	String line;
        	while((line = br.readLine()) != null) {
        		String[] items = line.split("\t");
        		List<String> words = new ArrayList<>();
        		for (int j = 1; j <= i; j++){
        			words.add(items[j]);
        		}
        		wordFrequencyMap.put(words, items[0]);
        	}
        	System.out.println("Klar");
        	br.close();
        } 
	}
	

    Double getWordFrequency(String word, List<String> wordsBefore){
        /**
         * Tar ett ord som sträng och en lista med de tidigare orden, ger tillbaka slh
         */
        wordsBefore.add(word);
        	String tempFreq = wordFrequencyMap.get(wordsBefore);
        	if(tempFreq==null){
        		return Double.NEGATIVE_INFINITY;
        	}
            return Double.parseDouble(wordFrequencyMap.get(wordsBefore));
    }

	Object[][] getWordsByFrequency(List<String> words, String currentSentence){
        Object[][] wordFrequencyArray  = new Object[words.size()][2];

        // Kolla hur många ord som finns innan.
        // Lägger till en extra sträng för att hantera så längden blir korrekt om det inte finns några tidigare ord
        String tempSentence = currentSentence + "controlStringXYZ";
        int numberOfEarlierWords = tempSentence.split(" ").length - 1;
        String[] wordsInSentence = currentSentence.split(" ");

        // Reglerar så den inte tar fler än fem tidigare ord
        if (numberOfEarlierWords > 5) {
            numberOfEarlierWords = 5;
        }

        // Lägger över orden i en ArrayList eftersom hashMappen förväntar sig en sådan
        List<String> wordsBefore = new ArrayList<>();
        for (int i = numberOfEarlierWords; i >= 1; i--){
            wordsBefore.add(wordsInSentence[wordsInSentence.length - i ]);
        }
        
        // Lägger in ord och sannolikhet i Arrayen
        boolean noPropabilityForWord = true;
        for (int i = 0; i < words.size(); i++) {
            String tmpWord = words.get(i);
            Double tmpFreq = getWordFrequency(tmpWord, wordsBefore);
            wordsBefore.remove(wordsBefore.size()-1);
            wordFrequencyArray[i][1] = tmpWord;
            wordFrequencyArray[i][0] = tmpFreq;
            if(tmpFreq!=Double.NEGATIVE_INFINITY){
            	noPropabilityForWord=false;
            }   
         }
        
        /*
        Körs bara om alla frekvenser(sannolikheter senare) är noll
        Anropar sig själv rekursivt med färre ord bakåt (wordsBefore) för varje rekursion.
        Om inget ord bakåt är kvar så tar den den enda lösningen
        Detta för att returnera en mening/ord som finns i tabellen hellre än ett med sannolikhet 0
        */
        if(noPropabilityForWord && wordsBefore.size()!=0){
        	wordsBefore.remove(0);
        	StringBuilder listString = new StringBuilder();
        	for (String s : wordsBefore)
        	     listString.append(s+" ");
        	return getWordsByFrequency(words, listString.toString());
        }
        
        return  getListByFrequency(wordFrequencyArray);
	}
	
	Object[][] getListByFrequency(Object[][] wordFrequencyArray){
		Arrays.sort(wordFrequencyArray, new Comparator<Object[]>() {
            public int compare(Object[] f1, Object[] f2) {
                Double freq1 = (Double) (f1[0]);
                Double freq2 = (Double) (f2[0]);
                return Double.compare(freq2, freq1);
                }});
		return wordFrequencyArray;
	}
}

