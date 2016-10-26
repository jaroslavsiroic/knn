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
}
