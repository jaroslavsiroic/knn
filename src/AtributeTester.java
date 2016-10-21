import java.util.ArrayList;

/**
 * Created by kosciuszko on 16.10.21.
 */
public class AtributeTester {
    private Statistics statistics = new Statistics();






    public ArrayList<Model> setCharacteristicToZero(ArrayList<Model> args, int charcteristic) {

        ArrayList<Model> arrayList = new ArrayList<>();

        if (args.get(0).vector[charcteristic] != 0) {
            for (Model model : arrayList) {
                model.vector[charcteristic] = 0;
            }

        }
        return arrayList;
    }

    public ArrayList<Model> getBestSet (ArrayList<ArrayList<Model>> setOfSets, int k) {
        double bestPercent = 0;
        int bestSetIndex = 0;
        double perc;
        for (int i = 0; i < setOfSets.size(); i++) {
            perc = statistics.calcPercentage(k, setOfSets.get(i));

            if (perc > bestPercent) {
                bestPercent = perc;
                bestSetIndex = i;
            }
        }
        return setOfSets.get(bestSetIndex);
    }




}
