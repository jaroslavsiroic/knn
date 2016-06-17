import java.util.ArrayList;

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
            guess = knn.init(ReadFile.learningSet,ReadFile.trainingSet.get(randNum),20);
            System.out.print("Checking the "+randNum+" number...");
            System.out.print(".. Hmm, I guess the number is a "+guess+"... ");
            if (guess == ReadFile.learningSet.get(randNum).iClass) System.out.print("and I'm right!!\n");
            else System.out.print("and I'm wrong :( it was "+ReadFile.learningSet.get(randNum).iClass+"\n");
            System.out.println("-------------------");
        }
    }
    public static double calcPercentage(int k, ArrayList<Model> set, int iterations){
        Model model;
        int rightGuesses = 0;
        int guess;
        int randNum;
        for (int i = 0; i < iterations; i++){
            randNum = (int) (Math.random() * set.size());
            model = set.get(randNum);
            set.remove(randNum);
            guess = knn.init(set,model,k);
            if (guess == model.iClass)
                rightGuesses++;
            set.add(model);
        }
        return percent(iterations, rightGuesses);
    }
    public static void statistics() {
        System.out.println("Iterations [100]");

        System.out.println("Learning set:");
        statOnSet(ReadFile.learningSet,100);
        System.out.println("Training set:");
        statOnSet(ReadFile.trainingSet,100);
    }

    public static void statOnSet(ArrayList<Model> set, int iterations){
        for (int i = 0; i < kValues.length; i++) {
            System.out.println("k = "+kValues[i]+" prcnt = "+calcPercentage(kValues[i],set, iterations)+"%");
        }
    }
    public static void main(String[] args) {
        ReadFile.readFile();
        statistics();

        //todo split set -> trainingSet learningSet
        //todo choose random model from different set
        //todo calc distance to model form each set model
        //todo get k nearest neighbours
        //todo determine the classhhhiuhui
        //todo check if class is right (AI got it right!?)
        //todo calc percentage (from 100 calculations)
    }
}
