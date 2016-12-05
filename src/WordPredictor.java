import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Martin Tornkvist on 2016-11-29.
 */
public class WordPredictor {
	Map<String, String> alphabet = new HashMap<String, String>();
	// Vår hasmap med alla possible words
	Map<String, List<String>> words = new HashMap<>();
    
	public WordPredictor() throws IOException {
		createAlphabet();
		createWordMap();
	}

	//La till här att den skall returnera något även om ordet inte finns i ordlistan.
	//annars ger den error.
	//Skulle kunna lägga till så att den kastar om/tarbort/lägger till siffror LOKALT för att testa för 
	//feltryck
	public List<String> getWordFromNum(String num){
		List<String> possibleWords = words.get(num);
		if(possibleWords!=null){
			return possibleWords;
		}
		List<String> emptyList  = new ArrayList<String>();
		emptyList.add("finnsej");
		return emptyList;
	}
	
	//Skapar hashmapen för alla ord
	private void createWordMap() throws IOException {
		//BufferedReader ska tydligen vara snabbare än scanner
        BufferedReader br = new BufferedReader(new FileReader("words.txt"));

        // Fyller ut hashmap med alla möjliga ord
        String line;
        String numWord;
        while ((line = br.readLine()) != null) {
            // Den här raden kan vi skippa när vi lägger in uppercase letters i alphabet
            // Den här gör även att vi kan två instancer av samma ord i hashmappen.
            // tex Satan och satan, blir satan och satan. Det slipper vi när vi har uppercase letters i alphabet
            line = line.toLowerCase();
            
            numWord = "";
            for (char c: line.toCharArray()) {
                    numWord += alphabet.get(String.valueOf(c));
                   
            }
            if (words.containsKey(numWord)) {
                List<String> currentPossibleWords = words.get(numWord);
                currentPossibleWords.add(line);
                words.put(numWord, currentPossibleWords);
            }
            else {
                List<String> possibleWords = new ArrayList<>();
                possibleWords.add(line);
                if(possibleWords.equals("a")){
                }
                words.put(numWord, possibleWords);
            }
        }
        br.close();
	}
	
	// Ska skriva om den här till en snygg for-loop som även lägger in stora bokstäver
    // Alternativt lägga in allt i en fil som vi sedan läser in. Kan snacka om vilket som blir snyggast
    // Skapar alphabet
	private void createAlphabet() {
		alphabet.put("a", "2");
		alphabet.put("b", "2");
		alphabet.put("c", "2");
		
		alphabet.put("d", "3");
		alphabet.put("e", "3");
		alphabet.put("f", "3");
		
		alphabet.put("g", "4");
		alphabet.put("h", "4");
		alphabet.put("i", "4");
		
		alphabet.put("j", "5");
		alphabet.put("k", "5");
		alphabet.put("l", "5");
		
		alphabet.put("m", "6");
		alphabet.put("n", "6");
		alphabet.put("o", "6");
		
		alphabet.put("p", "7");
		alphabet.put("q", "7");
		alphabet.put("r", "7");
		alphabet.put("s", "7");
		
		alphabet.put("t", "8");
		alphabet.put("u", "8");
		alphabet.put("v", "8");
		
		alphabet.put("w", "9");
		alphabet.put("x", "9");
		alphabet.put("y", "9");
		alphabet.put("z", "9");
	}
}

