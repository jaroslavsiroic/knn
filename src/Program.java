import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static final int nClass = 24;
    public static final int nRecords = 10000;
    public static Knn knn = new Knn();
    public static int[] kValues = {1, 2, 3, 5, 7, 11, 21, 51};

    public static double percent ( double checktimes, double matchtimes){
        return ((matchtimes / checktimes)) * 100;
    }
    public static void guessing(){
        int guess;
        int randNum;
        for (int i = 0; i < 20; i++){
            randNum = (int) (Math.random() * ReadFile.trainingSet.size());
            guess = knn.init(ReadFile.trainingSet,ReadFile.trainingSet.get(randNum),20);
            System.out.print("Checking the "+randNum+" number...");
            System.out.print(".. Hmm, I guess the number is a "+guess+"... ");
            if (guess == ReadFile.trainingSet.get(randNum).iClass) System.out.print("and I'm right!!\n");
            else System.out.print("and I'm wrong :( it was "+ReadFile.trainingSet.get(randNum).iClass+"\n");
            System.out.println("-------------------");
        }
    }
    public static double calcPercentage(int k, ArrayList<Model> set, int iterations){
        Model model;
        int rightGuesses = 0;
        int guess;

        for (int i = 0; i < iterations; i++){
            //randNum = (int) (Math.random() * set.size());
            model = set.get(i%set.size());
            set.remove(model);
            guess = knn.init(set,model,k);
            if (guess == model.iClass)
                rightGuesses++;
            set.add(model);
        }
        return percent(iterations, rightGuesses);
    }
    public static void statistics(int iterations) throws InputMismatchException{
        if(iterations > 10000) throw new InputMismatchException("Number is too big");

        System.out.println("Iterations ["+iterations+"]");

        System.out.println("Training set:");
        statOnSet(ReadFile.trainingSet,iterations);
        System.out.println("Testing set:");
        statOnSet(ReadFile.testSet,iterations);
    }

    public static void statOnSet(ArrayList<Model> set, int iterations){
        for (int i = 0; i < kValues.length; i++) {
            System.out.println("k = "+kValues[i]+" prcnt = "+calcPercentage(kValues[i],set, iterations)+"%");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReadFile.readFile();

        try {
            System.out.println("Enter iteration num. (Stand: [100]; Max: [10000])");
            statistics(scanner.nextInt());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage()+". Running stand.");
            statistics(100);
        }

    }
}
