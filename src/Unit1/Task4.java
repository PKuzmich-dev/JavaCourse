package Unit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task4 {
    public static void main(String[] args) throws IOException {
        double  a;
        double  b;
        double  c;
        double  d;
        char    c178 = 178;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите константу a:");
        a = Double.parseDouble(br.readLine());

        System.out.print("Введите константу b:");
        b = Double.parseDouble(br.readLine());

        System.out.print("Введите константу c:");
        c = Double.parseDouble(br.readLine());

        System.out.println("Решаем квадратное уравнение " + a + "x" + c178 + ((b<0) ? " - " : " + ")
                           + Math.abs(b) + "x" + ((c<0) ? " - " : " + ") + Math.abs(c) + "=0");

        d = Math.pow(b, 2) - 4 * a * c;

        if (d < 0) {
            System.out.println("Квадратное уравнение не имеет корней");
        }
        else if (d == 0) {
            System.out.println("Квадратное уравнение имеет один корень: " + ((-b) / 2 * a));
        }
        else {
            System.out.println("Квадратное уравнение имеет два корня: " + (((-b) + Math.sqrt(d)) / (2 * a)) + " и "
                               + (((-b) - Math.sqrt(d)) / (2 * a)));
        }

    }
}
