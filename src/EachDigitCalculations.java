

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class EachDigitCalculations {
    //input: training testing sets, splitting into each digit arraylist and calculating for each percentage

    private ArrayList<Model> testSet;
    private Statistics statistics = new Statistics();
    private HashMap<Integer, ArrayList<Model>> testingSets = new HashMap();
    private ArrayList<Model> trainingSet;

    public EachDigitCalculations() {
        restore();
    }

    private void restore() {
        testSet = Program.clone(ReadFile.testSet);
        trainingSet = Program.clone(ReadFile.trainingSet);
        testingSets.clear();
        for (Model model : testSet) {
            if (!testingSets.containsKey(model.iClass)) {
                testingSets.put(model.iClass, new ArrayList<>());
            }
            testingSets.get(model.iClass).add(model);
        }
        testingSets.put(10, testSet);
    }

    public void calculatePercentage(int k) {
        // eliminating exIndex by one from TrainingSet and set -> {digit set, trainingSet}
        // calc percentage
        // give next set, from set eliminate exIndex
        // repeat
        restore();
        double stats;
        System.out.println();
        System.out.println("Calculating with k = "+k);
        System.out.println("---------------------------------------");
        for (Integer j : testingSets.keySet()) {
            stats = statistics.calcPercentage(k, trainingSet, testingSets.get(j));
            System.out.println("Set: "+j+" percent: "+stats+"%");

        }
        System.out.println("---------------------------------------");
        System.out.println();
    }


    public void calculatePercentage(int k, int[] exIndexes) {
        // eliminating exIndex by one from TrainingSet and set -> {digit set, trainingSet}
        // calc percentage
        // give next set, from set eliminate exIndex
        // repeat
        double stats;
        ArrayList<Model> tmp;

        restore();
        calculatePercentage(k);
        System.out.println();
        System.out.println("Calculating with k = "+k);
        System.out.println("---------------------------------------");
        for (int i = exIndexes.length-1; i >= 0 ; i--) {

            System.out.print("Excluded indexes: [ ");
            for (int w = exIndexes.length-1; w >= i; w--) {
                System.out.print(exIndexes[w]+", ");
            }
            System.out.print("]\n");

            trainingSet = excludeAttr(trainingSet, exIndexes[i]);
            for (Integer j : testingSets.keySet()) {

                tmp = excludeAttr(testingSets.get(j), exIndexes[i]);
                testingSets.get(j).clear();
                testingSets.get(j).addAll(tmp);

                stats = statistics.calcPercentage(k, trainingSet, testingSets.get(j));
                System.out.println("Set: "+j+" percent: "+stats+"%");

            }
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }

    private ArrayList<Model> excludeAttr(ArrayList<Model> set, int attrIndex) {
        set = Program.clone(set);
        for (Model model : set) {
            model.vector = ArrayUtils.remove(model.vector, attrIndex);
            model.excludedIndexes.add(attrIndex);
        }
        return set;
    }


}
