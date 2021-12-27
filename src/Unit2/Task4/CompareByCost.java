package Unit2.Task4;

import Unit2.Task1.Stationery;

import java.util.Comparator;

public class CompareByCost implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {
        return Double.compare(o1.getCost(), o2.getCost());
    }
}
