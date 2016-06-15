
public class Program {
    public static final int nClass = 24;
    public static final int nRecords = 10000;

    public static void main(String[] args) {
        ReadFile.readFile();

        Knn knn;
        int guess;
        int randNum;
        for (int i = 0; i < 20; i++){
            randNum = (int) (Math.random() * ReadFile.trainingSet.size());
            knn = new Knn(ReadFile.learningSet,ReadFile.trainingSet.get(randNum),20);
            guess = knn.init();
            System.out.print("Checking the "+randNum+" number...");
            System.out.print(".. Hmm, I guess the number is a "+guess+"... ");
            if (guess == ReadFile.learningSet.get(randNum).iClass) System.out.print("and I'm right!!\n");
            else System.out.print("and I'm wrong :( it was "+ReadFile.learningSet.get(randNum).iClass+"\n");
            System.out.println("-------------------");
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
