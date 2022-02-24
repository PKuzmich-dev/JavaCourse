package Unit5.Task1;

import javax.lang.model.SourceVersion;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Task1 {
    public static void main(String[] args) {
        HashMap<String, Integer> words = getWords();
        writeToFile(words);
    }

    static void writeToFile(HashMap<String, Integer> words){
        try(OutputStream os = new FileOutputStream(".\\src\\Unit5\\Task1\\result.txt")) {
            for(Map.Entry word : words.entrySet()){
                os.write((word.toString() + "\n").getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static HashMap<String, Integer> getWords(){
        HashMap<String, Integer> words = new HashMap<>();
        int count;

        try(InputStream is = new FileInputStream(".\\src\\Unit5\\Task1\\Task1.java")) {
            int b;
            StringBuffer sb = new StringBuffer();
            while((b = is.read()) != -1){
                if (b == 13 || b == 10) {
                    if(sb.length() > 0) {
                        for (String word : sb.toString().split(" |\\(|\\)")) {
                            if (SourceVersion.isKeyword(word)) {
                                if (words.containsKey(word))
                                    count = words.get(word) + 1;
                                else
                                    count = 1;
                                words.put(word, count);
                            }
                        }
                        sb = new StringBuffer();
                    }
                }
                else
                    sb.append((char) b);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
