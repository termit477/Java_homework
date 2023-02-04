package task_3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Дана прямоугольная карта размера MxN (N, M меньше 20)
 * На карте стоит фигура в точке(Х, Y), которая может ходить
 * на одну клеточку вправо или вниз за один ход
 * Посчитать количество маршрутов, которым фигура
 * может попасть в нижнюю правую клетку
 * 
 * Задачу разбить на методы (+)
 ** На карте могут быть стены (-)
 ** M и N могут быть до 1000 (Решено (при выводе 1000х1000, лучше отключить вывод
 * всей таблицы (строка 35), иначе будет отображаться пару минут))
 */
public class task_3 {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Integer> SizeArray = new ArrayList<>();
        ArrayList<Integer> StartPoint = new ArrayList<>();
        SizeArray.add(InputNumber("Введите размер поля (N): "));
        SizeArray.add(InputNumber("Введите размер поля (M): "));
        StartPoint.add(InputNumber("Введите точку начального положения (X): "));
        StartPoint.add(InputNumber("Введите точку начального положения (Y): "));

        BigInteger[][] Labyrinth = new BigInteger[SizeArray.get(0)][SizeArray.get(1)];
        Labyrinth = FillingTheMapWithZeros(Labyrinth, SizeArray, StartPoint);
        Labyrinth = NumberOfMoves(Labyrinth, SizeArray, StartPoint);

        PrintMap(Labyrinth);
        System.out.println("Количество возможных маршрутов: " + Labyrinth[SizeArray.get(0) - 1][SizeArray.get(1) - 1]);
    }


    public static Integer InputNumber(String text) {
        System.out.print(text);
        return input.nextInt();
    }


    public static BigInteger[][] FillingTheMapWithZeros(BigInteger[][] map, ArrayList<Integer> size, ArrayList<Integer> start) {
        for (Integer i = start.get(0); i < size.get(0); i++) {
            for (Integer j = start.get(1); j < size.get(1); j++) {
                map[i][j] = BigInteger.valueOf(0);
            }
        }
        return map;
    }


    public static BigInteger[][] NumberOfMoves(BigInteger[][] map, ArrayList<Integer> size, ArrayList<Integer> start) {
        for (Integer i = start.get(0); i < size.get(0); i++) {
            for (Integer j = start.get(1); j < size.get(1); j++) {
                if ((i == 0) && (j == 0) || (i == start.get(0)) | (j == start.get(1))) {
                    map[i][j] = BigInteger.valueOf(1);
                } else {
                    map[i][j] = map[i - 1][j];
                    map[i][j] = map[i][j].add(map[i][j - 1]);
                }
            }
        }
        return map;
    }


    public static void PrintMap(BigInteger[][] res) {
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length; j++) {
                System.out.printf(res[i][j] + "  |  ");
            }
            System.out.println();
        }
    }
}