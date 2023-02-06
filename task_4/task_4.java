import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * task_4
 */

public class task_4 {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Queue<Point2D> QueueOfNext = new LinkedList<>();
        int[][] Labirinth = new int[10][10];
        Point2D StartPoint = new Point2D(InputNumber("Введите x: "), InputNumber("Введите y: "));
        FillingTheLabyrinthWithWallsAndExit(StartPoint, Labirinth, QueueOfNext);

        while (QueueOfNext.isEmpty() == false) {
            Labirinth = CheckingThePossibilityOfAMove(Labirinth, PullPointOfQueue(QueueOfNext), QueueOfNext);
        }
        PrintMap(Labirinth);

    }

    public static Integer InputNumber(String text) {
        System.out.print(text);
        return input.nextInt();
    }

    public static void FillingTheLabyrinthWithWallsAndExit(Point2D start, int[][] lab, Queue<Point2D> qu) {
        lab[start.i][start.j] = 1;
        lab[1][3] = -1;
        lab[5][4] = -1;
        lab[6][4] = -1;
        lab[5][5] = -1;
        lab[7][8] = -1;
        lab[3][7] = -1;
        lab[8][8] = -2;
        qu.add(new Point2D(start.i, start.j));
    }

    private static Point2D PullPointOfQueue(Queue<Point2D> qu) {
        return qu.remove();
    }

    public static int[][] CheckingThePossibilityOfAMove(int[][] lab, Point2D point, Queue<Point2D> qu) {
        try {
            if (lab[point.i - 1][point.j] == 0) {
                lab[point.i - 1][point.j] = lab[point.i][point.j] + 1;
                qu.add(new Point2D(point.i - 1, point.j));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (lab[point.i][point.j + 1] == 0) {
                lab[point.i][point.j + 1] = lab[point.i][point.j] + 1;
                qu.add(new Point2D(point.i, point.j + 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (lab[point.i + 1][point.j] == 0) {
                lab[point.i + 1][point.j] = lab[point.i][point.j] + 1;
                qu.add(new Point2D(point.i + 1, point.j));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (lab[point.i][point.j - 1] == 0) {
                lab[point.i][point.j - 1] = lab[point.i][point.j] + 1;
                qu.add(new Point2D(point.i, point.j - 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return lab;
    }

    public static void PrintMap(int[][] lab) {
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab.length; j++) {
                System.out.print(lab[i][j] + " | ");
            }
            System.out.println();
        }
    }
}

class Point2D {
    public int i, j;

    public Point2D(int i, int j) {
        this.i = i;
        this.j = j;
    }
}