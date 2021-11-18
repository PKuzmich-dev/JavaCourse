package Unit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task1 {
    public static void main(String[] args) throws IOException {
        int arrLength;
        int i = 0;
        int r;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите размер массива:");
        arrLength = Integer.parseInt(br.readLine());
        if (arrLength < 1) {
            System.out.println("Размер массива не может быть меньше 1");
            return;
        }
        int array[] = new int[arrLength];

        /* первый вариант цикла для заполнения массива */
        do {
            r = (int) (Math.random() * 100);
            array[i] = 2 * r;
            i++;
        } while (i < arrLength);

        /* второй вариант цикла для вывода содержимого массива */
        System.out.print("Первоначальный вид массива: ");
        i = 0;
        while (i < arrLength) {
            System.out.print(array[i++] + " ");
        }

        /* третий вариант цикла для изменения массива */
        for (i = 1; i < arrLength; i += 2) {
            array[i] *= array[i - 1];
        }
        System.out.println();

        /* четвертый вариант цикла для вывода окончательной версии массива */
        System.out.print("Окончательный вид массива: ");
        for (int x : array) {
            System.out.print(x + " ");
        }
    }
}
