import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static ArrayList<Model> clone(ArrayList<Model> baseSet) {
        ArrayList<Model> arrayList = new ArrayList<>();
        for (Model model : baseSet) {
            arrayList.add(model.clone());
        }
        return arrayList;
    }

    public static ArrayList<String> significant(ArrayList<String> list) {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != "DEL") {
                output.add(i+"");
            }
        }
        return output;
    }
    public static ArrayList<String> eliminated(ArrayList<String> list) {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == "DEL") {
                output.add(i+"");
            }
        }
        System.out.println(output);
        return output;
    }


    public static void writeToTXT(int[] kValues, ArrayList<Model> set, PrintWriter writer, AtributeTester atributeTester, int attrToEx) {
        long startTime, endTime, totalTime;

        ArrayList<String> output;
        writer.println("Set.size = "+set.size());
        for (int i = 0; i < kValues.length; i++) {
            writer.println("k = "+kValues[i]);
            // time calculation
            startTime = System.currentTimeMillis();
            output = atributeTester.getBestAttr(clone(set), kValues[i], attrToEx);
            endTime = System.currentTimeMillis();
            totalTime = (endTime - startTime)/1000;
            System.out.println("Calculation time: "+totalTime);

            writer.println("Eliminated attribute indexes: "+eliminated(output));
            writer.println("Significant attribute indexes: "+significant(output));
        }
    }
    public static void main(String[] args) {
        ReadFile.readFile();
//        EachDigitCalculations ej = new EachDigitCalculations();
//        ej.calculatePercentage(3, new int[]{0, 1, 13, 15, 16, 18});
//        ej.calculatePercentage(6, new int[]{0, 1, 2, 4, 11, 16});
//        ej.calculatePercentage(9, new int[]{0, 2, 4, 12, 15, 16});
//        ej.calculatePercentage(12, new int[]{2, 3, 4, 6, 8, 16});
        attributeTester(8, 1);

    }

    public static void attributeTester(int threadCount, int attributesToEliminate) {
        int[] kValues = {3};
        AtributeTester atributeTester = new AtributeTester(threadCount);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("outputKNN.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writeToTXT(kValues, ReadFile.testSet, writer, atributeTester, attributesToEliminate);
//        writer.println();
//        writeToTXT(kValues, ReadFile.trainingSet, writer, atributeTester, attributesToEliminate);
        writer.close();
    }
}
