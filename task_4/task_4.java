import java.util.LinkedList;
import java.util.Queue;

/**
 * Реализовать волновой алгоритм
 */

public class task_4 {

    public static void main(String[] args) {

        int[][] Labirinth = CreatingAMaze.CreatingAMap();

        Queue<Point2D> QueueOfNext = new LinkedList<>();
        QueueOfNext.add(new Point2D(2, 2));
        Queue<Point2D> QueueOfBack = new LinkedList<>();
        QueueOfBack.add(new Point2D(11, 12));
        Queue<Point2D> ShortPath = new LinkedList<>();
        ShortPath.add(new Point2D(11, 12));

        while (QueueOfNext.isEmpty() == false) {
            Labirinth = WaveAlgorithm.CheckingThePossibilityOfAMove(Labirinth,
                    WaveAlgorithm.PullPointOfQueue(QueueOfNext), QueueOfNext);
        }
        while (QueueOfBack.isEmpty() == false) {
            Labirinth = WaveAlgorithm.FindTheShortPath(Labirinth,
                    WaveAlgorithm.PullPointOfQueue(QueueOfBack), QueueOfBack, ShortPath);
        }
        while (ShortPath.isEmpty() == false) {
            Labirinth = CreatingAMaze.FillingInThePath(Labirinth,
                    WaveAlgorithm.PullPointOfQueue(ShortPath));
        }
        String Result = CreatingAMaze.MapColor(Labirinth);
        System.out.println(Result);
    }
}

class CreatingAMaze {

    public static int[][] CreatingAMap() {
        int[][] map = {
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, 00, 00, 00, -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 01, 00, 00, 00, 00, -1, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, -1, 00, 00, -1, 00, 00, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, -1, 00, -1, -1, -1, -1, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, -1, 00, -1, 00, 00, -1, 00, 00, 00, 00, -1 },
                { -1, -1, -1, 00, -1, 00, -1, 00, 00, -1, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, -1, 00, -1, 00, 00, -1, -1, -1, 00, 00, -1 },
                { -1, 00, 00, 00, -1, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, -1, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, -1, -1, -1, -1, -1, -1, 00, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -2, 00, -1 },
                { -1, 00, 00, 00, -1, -1, -1, -1, -1, -1, -1, 00, 00, 00, -1 },
                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
        };
        return map;
    }

    public static int[][] FillingInThePath(int[][] lab, Point2D point) {
        lab[point.i][point.j] = -2;
        return lab;
    }

    public static String MapColor(int[][] map) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case -1:
                        sb.append(String.format("%4s", "█"));
                        break;
                    case -2:
                        sb.append(String.format("%4s", "╬"));
                        break;
                    default:
                        sb.append(String.format("%4d", map[i][j]));
                        break;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

class WaveAlgorithm {

    public static Point2D PullPointOfQueue(Queue<Point2D> qu) {
        return qu.remove();
    }

    public static int[][] CheckingThePossibilityOfAMove(int[][] lab,
            Point2D point, Queue<Point2D> next) {

        if ((lab[point.i - 1][point.j] == 00) || (lab[point.i - 1][point.j] == -2)) {
            lab[point.i - 1][point.j] = lab[point.i][point.j] + 1;
            next.add(new Point2D(point.i - 1, point.j));
        }
        if ((lab[point.i][point.j + 1] == 00) || (lab[point.i][point.j + 1] == -2)) {
            lab[point.i][point.j + 1] = lab[point.i][point.j] + 1;
            next.add(new Point2D(point.i, point.j + 1));
        }
        if ((lab[point.i + 1][point.j] == 00) || (lab[point.i + 1][point.j] == -2)) {
            lab[point.i + 1][point.j] = lab[point.i][point.j] + 1;
            next.add(new Point2D(point.i + 1, point.j));
        }
        if ((lab[point.i][point.j - 1] == 00) || (lab[point.i][point.j - 1] == -2)) {
            lab[point.i][point.j - 1] = lab[point.i][point.j] + 1;
            next.add(new Point2D(point.i, point.j - 1));
        }
        return lab;
    }

    public static int[][] FindTheShortPath(int[][] lab, Point2D point, Queue<Point2D> back, Queue<Point2D> way) {

        if (lab[point.i - 1][point.j] == lab[point.i][point.j] - 1) {
            way.add(new Point2D(point.i - 1, point.j));
            back.add(new Point2D(point.i - 1, point.j));
        } else if (lab[point.i][point.j + 1] == lab[point.i][point.j] - 1) {
            way.add(new Point2D(point.i, point.j + 1));
            back.add(new Point2D(point.i, point.j + 1));
        } else if (lab[point.i + 1][point.j] == lab[point.i][point.j] - 1) {
            way.add(new Point2D(point.i + 1, point.j));
            back.add(new Point2D(point.i + 1, point.j));
        } else if (lab[point.i][point.j - 1] == lab[point.i][point.j] - 1) {
            way.add(new Point2D(point.i, point.j - 1));
            back.add(new Point2D(point.i, point.j - 1));
        }
        return lab;
    }

}

class Point2D {
    public int i, j;

    public Point2D(int i, int j) {
        this.i = i;
        this.j = j;
    }
}