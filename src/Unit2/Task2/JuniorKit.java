package Unit2.Task2;

import Unit2.Task1.Marker;
import Unit2.Task1.Notepad;
import Unit2.Task1.Pen;
import Unit2.Task1.Stationery;
import java.util.ArrayList;
import java.util.Comparator;

public class JuniorKit {
    private ArrayList<Stationery> juniorKit = new ArrayList();

    public JuniorKit(){
        juniorKit.add(new Pen("ручка", 10));
        juniorKit.add(new Marker("маркер", 100, "черный"));
        juniorKit.add(new Notepad("блокнот", 100));
    }

    public  ArrayList<Stationery> getStationeryList(){
        return juniorKit;
    }

    void add(Stationery s){
        juniorKit.add(s);
    }

    public void compare(Comparator<Stationery> c){
        juniorKit.sort(c);
    }

    @Override
    public String toString() {
        return juniorKit.toString();
    }
}
