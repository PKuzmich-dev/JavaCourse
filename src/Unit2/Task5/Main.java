package Unit2.Task5;

public class Main {
    public static void main(String[] args) {
        MyFuncB funcb = x -> ((x % 13) == 0);
        System.out.println("17 делится на 13 - " + funcb.func(17));
        System.out.println("26 делится на 13 - " + funcb.func(26));

        MyFuncD funcd = (a , b, c) -> (b*b - 4*a*c);
        System.out.println("Дискриминант для a=3, b=-4, c=2 равен " + funcd.func(3, -4, 2));

        MyFuncSum<Integer> sumi = (a, b) -> a + b;
        MyFuncSum<Float> sumf = (a, b) -> a + b;
        MyFuncSum<Double> sumd = (a, b) -> a + b;

        System.out.println("сумма 10 и 20 - " + sumi.func(10, 20));
        System.out.println("сумма 1.5f и 2.5f - " + sumf.func(1.5f, 2.5f));
        System.out.println("сумма 38.1d и 4.5d - " + sumd.func(38.1d, 4.5d));
    }

    interface MyFuncB{
        boolean func(int a);
    }

    interface MyFuncD{
        double func(double a, double b, double c);
    }

    interface MyFuncSum<T>{
        T func(T a, T b);
    }
}
