import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NgramSorter {
	//Skapade en hashmap för alla
    Map<List<String>, String> wordFrequencyMap = new HashMap<>();

    // Den här finns med om man bara skulle vilja ha med tex 1-2gram eller 1-3gram lr liknande. värdet betyder Maximala nGram
	int nGramVersion;
	
	public NgramSorter(int version) throws IOException {
        nGramVersion = version;
        readFiles();
	}

	private void readFiles() throws IOException {
		//Tog bort översta raderna i filen
        for (int i = 1; i <= nGramVersion; i++){
        	String filename = "w" + i + "_.txt";
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
        }
        
	}
	
    // Tar ett ord som sträng och en lista med de tidigare orden, ger tillbaka frekvensen
    int getWordFrequency(String word, List<String> wordsBefore){
        wordsBefore.add(word);
        try {
            return Integer.parseInt(wordFrequencyMap.get(wordsBefore));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

	String getWordsByFrequency(List<String> words, String currentSentence){
        // TODO Behöver skriva om den här så den hanterar näre words.size() = null, tex trycker på space två gånger i rad
        Object[][] wordFrequencyArray  = new Object[words.size()][2];

        // Kolla hur många ord som finns innan. Vi måste lägga till en extra sträng för att hantera så längden blir
        // korrekt om det inte finns några tidigare ord
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
        
        //Lägger in ord och sannolikhet i Arrayen 
        //Har lagt ihop looparna
        int allFrequencysAreZero = 0;
        for (int i = 0; i < words.size(); i++) {
            String tmpWord = words.get(i);
            int tmpFreq = this.getWordFrequency(tmpWord, wordsBefore);
            wordsBefore.remove(wordsBefore.size()-1);
            wordFrequencyArray[i][1] = tmpWord;
            wordFrequencyArray[i][0] = tmpFreq;
            allFrequencysAreZero+=tmpFreq;
            }
        
        //Körs bara om alla frekvenser(sannolikheter senare) är noll
        //Anropar sig själv rekursivt med färre ord bakåt (wordsBefore) för varje rekursion. 
        //Om inget ord bakåt är kvar så tar den den enda lösningen
        //Detta för att returnera en mening/ord som finns i tabellen hellre än ett med sannolikhet 0
        if(allFrequencysAreZero==0 && wordsBefore.size()!=0){
        	wordsBefore.remove(0);
        	StringBuilder listString = new StringBuilder();
        	for (String s : wordsBefore)
        	     listString.append(s+" ");
        	return getWordsByFrequency(words, listString.toString());
        }
        
        String bestWord = getHighestFrequency(wordFrequencyArray);
        return  bestWord;
        
	}
	
	//Skapade denna funktion som sorterar, flyttade ut koden från getWordsByFrequency
    //Sorterar Arrayen med ord,frekvens och returnerar den med högst frekvens
	String getHighestFrequency(Object[][] wordFrequencyArray){
		Arrays.sort(wordFrequencyArray, new Comparator<Object[]>() {
            public int compare(Object[] f1, Object[] f2) {
                Integer freq1 = (Integer) (f1[0]);
                Integer freq2 = (Integer) (f2[0]);
                return freq2.compareTo(freq1);
                }});
		return (String)wordFrequencyArray[0][1];
	}
}

