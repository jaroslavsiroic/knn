import java.util.ArrayList;

public class Statistics {

    //input: k, set;
    //output: percentage;

    public Knn knn = new Knn();

    public double percent ( double checktimes, double matchtimes){
        return ((matchtimes / checktimes)) * 100;
    }

    public double calcPercentage(int k, ArrayList<Model> set) {
        Model model;
        int rightGuesses = 0;
        int guess;

        for (int i = 0; i < set.size(); i++) {
            model = set.get(i);
            set.remove(model);
            guess = knn.init(set,model,k);
            if (guess == model.iClass) {
                rightGuesses++;
            }

            set.add(model);
        }
        return percent(set.size(), rightGuesses);
    }

    public double calcPercentage(int k, ArrayList<Model> trainingSet, ArrayList<Model> testingSet) {
        // trainingSet 70% (db set) & testingSet 30% for, well, testing
        int rightGuesses = 0;
        int guess;

        for (int i = 0; i < testingSet.size(); i++) {
            guess = knn.init(trainingSet, testingSet.get(i), k);
            if (guess == testingSet.get(i).iClass) {
                rightGuesses++;
            }
        }
        return percent(testingSet.size(), rightGuesses);
    }
}
