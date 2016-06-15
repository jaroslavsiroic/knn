import java.util.ArrayList;

public class Program {
    public static final int nClass = 24;
    public static final int nRecords = 10000;

    public static void main(String[] args) {
        ArrayList<Model> modelList = ReadFile.readFile();
        for(Model m : modelList){
            System.out.println("\nclass: "+m.iClass+"\nvector:");
            for (int i = 0; i < m.vector.length; i++) {
                System.out.print(m.vector[i]+" ");
            }
        }
        //todo split set -> trainingSet learningSet
        //todo choose random model from different set
        //todo calc distance to model form each set model
        //todo get k nearest neighbours
        //todo determine the class
        //todo check if class is right (AI got it right!?)
        //todo calc percentage (from 100 calculations)
    }
}
