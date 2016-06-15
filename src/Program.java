import java.util.ArrayList;

public class Program {
    public static final int nClass = 24;
    public static final int nRecords = 10000;

    public static void main(String[] args) {
        ReadFile.readFile();
        System.out.println("learning set="+ReadFile.learningSet.size());
        System.out.println("training set="+ReadFile.trainingSet.size());
        //todo split set -> trainingSet learningSet
        //todo choose random model from different set
        //todo calc distance to model form each set model
        //todo get k nearest neighbours
        //todo determine the class
        //todo check if class is right (AI got it right!?)
        //todo calc percentage (from 100 calculations)
    }
}
