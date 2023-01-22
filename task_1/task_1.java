package task_1;

import java.util.Scanner;

/**
 * Написать программу вычисления n-ого треугольного числа.
 */
public class task_1 {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int n = getNumber("Введите N: ");
        double triangular_n = triangular(n);
        int result = (int) triangular_n;
        System.out.println("Треугольное число, при N = " + n + ", будет равно: " + result);
    }

    // Ввод числа
    public static int getNumber(String text) {
        System.out.print(text);
        return input.nextInt();
    }

    // Получение n-ого треугольного числа
    public static double triangular(int number) {
        double triang_n = 0.5 * number * (number + 1);
        return triang_n;
    }
}
