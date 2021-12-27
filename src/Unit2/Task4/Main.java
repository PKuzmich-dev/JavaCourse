package Unit2.Task4;

import Unit2.Task1.Stationery;
import Unit2.Task2.JuniorKit;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        JuniorKit jk = new JuniorKit();
        System.out.println("Набор новичка до сортировки:");
        System.out.println(jk);

        CompareByCost compCost = new CompareByCost();
        CompareByName compName = new CompareByName();
        Comparator<Stationery> compCostThenName = compCost.thenComparing(compName);

        jk.compare(compCostThenName);
        System.out.println("Набор новичка после сортировки по стомости и наименованию");
        System.out.println(jk);

        jk.compare(new CompareByName());
        System.out.println("Набор новичка после сортировки по наименованию");
        System.out.println(jk);

        jk.compare(compCost);
        System.out.println("Набор новичка после сортировки по стомости");
        System.out.println(jk);

    }
}
