import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

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
        return output;
    }


    public static void writeToTXT(int[] kValues, ArrayList<Model> set, PrintWriter writer, AtributeTester atributeTester, int attrToEx) {
        ArrayList<String> output;
        writer.println("Set.size = "+set.size());
        for (int i = 0; i < kValues.length; i++) {
            writer.println("k = "+kValues[i]);
            output = atributeTester.getBestAttr(set, kValues[i], attrToEx);
            writer.println("Eliminated attribute indexes: "+eliminated(output));
            writer.println("Significant attribute indexes: "+significant(output));
        }
    }
    public static void main(String[] args) {
        ReadFile.readFile();
        int[] kValues = {3, 9};
        AtributeTester atributeTester = new AtributeTester();
        PrintWriter writer = null;
        ArrayList<Model> list = new ArrayList<>();
        ArrayList<Model> list1 = new ArrayList<>();
        ArrayList<Model> list2 = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list1.add(ReadFile.testSet.get(i));
            list2.add(ReadFile.trainingSet.get(i));
        }
        try {
            writer = new PrintWriter("outputKNN.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writeToTXT(kValues, list1, writer, atributeTester, 6);
        writer.println();
        writeToTXT(kValues, list2, writer, atributeTester, 6);
        writer.close();

    }
}
