package Unit5.Task2;

import javax.lang.model.SourceVersion;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Task2 {
    public static void main(String[] args) {
        HashMap<String, Integer> words = getWords();
        writeToFile(words);
    }

    static void writeToFile(HashMap<String, Integer> words){
        try(Writer w = new FileWriter(".\\src\\Unit5\\Task2\\result.txt")) {
            for(Map.Entry word : words.entrySet()){
                w.write(word.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static HashMap<String, Integer> getWords(){
        HashMap<String, Integer> words = new HashMap<>();
        int count;

        try(Reader r = new FileReader(".\\src\\Unit5\\Task1\\Task1.java");
            BufferedReader br = new BufferedReader(r)) {
            String line;
            while((line = br.readLine()) != null){
                for (String word : line.split(" |\\(|\\)")) {
                    if (SourceVersion.isKeyword(word)) {
                        if (words.containsKey(word))
                            count = words.get(word) + 1;
                        else
                            count = 1;
                        words.put(word, count);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
