package Unit6.Task1;

import java.io.*;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        String filename;

        try(Scanner scr = new Scanner(System.in)){
            System.out.println("Введите файл:");
            filename = scr.next();
        }
        try(FileReader fr = new FileReader(filename)){
            properties.load(fr);
        }

        Map<Object, Object> map = new HashMap<>(properties);
        System.out.println(map);
    }
}
