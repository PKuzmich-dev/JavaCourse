package Unit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2 {
    public static void main(String[] args) throws IOException {
        double a;
        double b;
        double h;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите начало отрезка(a):");
        a = Double.parseDouble(br.readLine());

        System.out.print("Введите конец отрезка(b):");
        b = Double.parseDouble(br.readLine());

        System.out.print("Введите шаг(h):");
        h = Double.parseDouble(br.readLine());

        for (double x = a; x <= b; x += h) {
            System.out.println(x + "\t" + Math.round((Math.tan(2 * x) - 3) * 100)/100d);
        }
    }
}
