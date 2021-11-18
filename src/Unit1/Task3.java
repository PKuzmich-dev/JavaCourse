package Unit1;

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        int array[][] = new int[5][8];
        int arrayMinMax[][] = new int[5][2];
        int min;
        int max;
        int r = 0;

        /* заполнение массива */
        for(int i = 0; i < array.length; i++) {
            for(int j=0; j < array[i].length; j++)
                array[i][j] = (int) (Math.random() * 1000);
        }

        for(int a[] : array) {
            min = max = a[0];
            for(int x : a) {
                min = Math.min(x, min);
                max = Math.max(x, max);
            }
            arrayMinMax[r][0] = min;
            arrayMinMax[r++][1] = max;
        }

        System.out.println("Первый массив:");
        for(int a[] : array) {
            System.out.println(Arrays.toString(a));
        }

        System.out.println("Второй массив:");
        for(int a[] : arrayMinMax) {
            System.out.println(Arrays.toString(a));
        }
    }
}
