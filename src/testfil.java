import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Martin Tornkvist on 2016-11-30.
 */
public class testfil {
    public static void main(String[] args) throws IOException {
        HashMap<List<String>, String> testMap = new HashMap<>();
        List<String> testList = new ArrayList<>();
        testList.add("Jag");
        testList.add("heter");
        testMap.put(testList, "rätt");


        List<String> testList2 = new ArrayList<>();
        testList2.add("Jag");
        testList2.add("heter");
        testList2.remove(testList.size()-1);
        testMap.put(testList2, "Yay");

        List<String> testList3 = new ArrayList<>();
        testList3.add("Jag");
        testList3.add("Heter");

        String answer = testMap.get(testList2);
        System.out.println(answer);

        String answer2 = testMap.get(testList3);
        System.out.println(answer2);

        List<String> testList4 = new ArrayList<>();
        testList4.add("Jag");
        String answer3 = testMap.get(testList4);
        System.out.println(answer3);

        Map<List<String>, String> wordFrequencyMap2 = new HashMap<>();

        for (int i = 2; i <= 2; i++){

            String filename = "w" + i + "_.txt";
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while((line = br.readLine()) != null) {
                String[] items = line.split("\t");
                List<String> words = new ArrayList<>();
                for (int j = 1; j <= i; j++){
                    words.add(items[j]);
                }
                wordFrequencyMap2.put(words, items[0]);
            }
            br.close();
        }
        
        List<String> testList6 = new ArrayList<>();
        testList6.add("my");
        testList6.add("name");
        String freq = wordFrequencyMap2.get(testList6);
        System.out.println("freq: " + freq);

    }
}
