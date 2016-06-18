import java.util.ArrayList;

public class Program {
    public static final int nClass = 24;
    public static final int nRecords = 10000;
    public static Knn knn = new Knn();
    public static int[] kValues = {1, 2, 3, 5, 7, 11, 21, 51};

    public static double percent ( double checktimes, double matchtimes){
        return ((matchtimes / checktimes)) * 100;
    }

    public static double calcPercentage(int k, ArrayList<Model> set, int iterations){
        Model model;
        int rightGuesses = 0;
        int guess;
        //int randNum;
        for (int i = 0; i < iterations; i++){
            //randNum = (int) (Math.random() * set.size());
            model = set.get(i+100);
            set.remove(model);
            guess = knn.init(set,model,k);
            if (guess == model.iClass)
                rightGuesses++;
            set.add(model);
        }
        return percent(iterations, rightGuesses);
    }
    public static void statistics(int iterations) {
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
        ReadFile.readFile();
        statistics(100);

    }
}
