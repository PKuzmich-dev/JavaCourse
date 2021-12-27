package Unit2.Task4;

import Unit2.Task1.Stationery;

import java.util.Comparator;

public class CompareByName implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
