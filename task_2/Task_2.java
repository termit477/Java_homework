/**
 * Реализовать функцию возведения числа а в степень b. a, b ∈ Z. Сводя количество выполняемых действий к минимуму. 
 * Пример 1: а = 3, b = 2, ответ: 9 
 * Пример 2: а = 2, b = -2, ответ: 0.25
 * Пример 3: а = 3, b = 0, ответ: 1
 * Пример 4: а = 0, b = 0, ответ: не определено
 * Пример 5
 * входные данные находятся в файле input.txt в виде
 * b 3
 * a 10
 * Результат нужно сохранить в файле output.txt
 */
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Task_2 {
    public static void main(String[] args) throws IOException {
        String InputFileName = "task_2/input.txt";
        String OutputFileName = "task_2/output.txt";
        String Result = null;
        ArrayList<String> List = GetInfoFromFile(InputFileName);
        ArrayList<Integer> Numbers = new ArrayList<>();
        Numbers.add(ReturnNumber(List, "a"));
        Numbers.add(ReturnNumber(List, "b"));
        if (CheckingForZero(Numbers) == true) {
            Result = PowOfNumber(Numbers).toString();
            WriteToFile(Result, OutputFileName);
        } else {
            Result = "Не определено";
            WriteToFile(Result, OutputFileName);
        }
        System.out.println(Result);
    }

    public static ArrayList<String> GetInfoFromFile(String name) throws IOException {
        Path path = Path.of(name);
        ArrayList<String> list = (ArrayList<String>) Files.readAllLines(path);
        return (ArrayList<String>) list;
    }

    public static void WriteToFile(String res, String name) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name, true), "UTF-8"));
            writer.write(res);
            writer.newLine();
            writer.close();
    }

    public static Integer ReturnNumber(ArrayList<String> mass, String findString) {
        Integer Num = null;
        for (int i = 0; i < mass.size(); i++) {
            String str = mass.get(i).toString();
            if (str.indexOf(findString) != -1) {
                int pos = str.indexOf(" ");
                Num = Integer.parseInt(str.substring(pos + 1));
            }
        }
        return (Integer) Num;
    }

    public static Boolean CheckingForZero(ArrayList<Integer> mass) {
        if (mass.get(0) != 0 || mass.get(1) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Double PowOfNumber(ArrayList<Integer> array) {
        return (Double) Math.pow(array.get(0), array.get(1));
    }
}