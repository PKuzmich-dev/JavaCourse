package Unit2.Task2;

import Unit2.Task1.Notepad;
import Unit2.Task1.Pen;
import Unit2.Task1.Stationery;
import java.util.ArrayList;
/**
 * либо я не понял задание, либо просто можно задание 1 сделать так, чтобы в
 * задании 2 практически ничего не надо было делать
 *
 * @author Кузмич Павел
 * @version 1.0
 */
public class Task2 {
    public static void main(String[] args) {
        Stationery p = new Pen("ручка", 10);
        Notepad n = new Notepad("блокнот", 100);

        ArrayList<Stationery> juniorKit = new ArrayList();

        juniorKit.add(p);
        juniorKit.add(n);
    }
}
