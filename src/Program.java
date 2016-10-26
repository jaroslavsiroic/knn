import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        ReadFile.readFile();
        AtributeTester atributeTester = new AtributeTester();
        System.out.println(atributeTester.getBestAttr(ReadFile.testSet,3, 2));
    }
}
