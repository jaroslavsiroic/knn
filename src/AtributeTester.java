import java.util.ArrayList;

public class AtributeTester {
    private Statistics statistics = new Statistics();

    private ArrayList<String> backTrack(double[] arr, ArrayList<Integer> excludedIndexlist) {
        ArrayList<Double> list = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        for (double d : arr){
            list.add(d);
        }
        for (int i = excludedIndexlist.size()-1; i >= 0; i--) {
            list.add(excludedIndexlist.get(i), Double.MAX_VALUE);
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == Double.MAX_VALUE) {
                output.add("DEL");
            } else {
                output.add(i+"");
            }
        }
        return output;
    }

    public void setArrayZeros(ArrayList<Model> set, int attrIndex) {
        for (Model model : set) {
            model.vector = ArrayUtils.remove(model.vector, attrIndex);
            model.excludedIndexes.add(attrIndex);
        }
    }
    //clone array
    public ArrayList<Model> clone(ArrayList<Model> baseSet) {
        ArrayList<Model> arrayList = new ArrayList<>();
        for (Model model : baseSet) {
            arrayList.add(model.clone());
        }
        return arrayList;
    }
    // cloning is fun
    public ArrayList<ArrayList<Model>> arrayCloner(ArrayList<Model> baseSet, int cloneNum) {
        ArrayList<ArrayList<Model>> setOfSets = new ArrayList<>();
        for (int i = 0; i < cloneNum; i++) {
            setOfSets.add(clone(baseSet));
        }
        return setOfSets;
    }
    //lets zero all the stuff
    public ArrayList<ArrayList<Model>> setEachArrayZeros(ArrayList<ArrayList<Model>> setOfSets) {
        for (int i = 0; i < setOfSets.size(); i++) {
            setArrayZeros(setOfSets.get(i), i);
        }
        return setOfSets;
    }

    // I want to exclude attrToEx (ec. 2) attr and find best attributes
    public ArrayList<String> getBestAttr(ArrayList<Model> initSet, int k, int attrToEx) {
        //so lets do it
        System.out.println("Exclude "+attrToEx+" attr with k="+k);
        ArrayList<Model> bestSet = getBestOfTheBest(initSet, k, attrToEx);
        Model bestModel = bestSet.get(0);
        System.out.println("Done");
        return  backTrack(bestModel.vector, bestModel.excludedIndexes);
    }

    // magical method here
    public ArrayList<Model> getBestOfTheBest(ArrayList<Model> initSet, int k, int attrToEx) {
        //to get best set, we need to create a bunch of clone sets here, and iterate throw this process attrToEx (ec. 2) times
        ArrayList<Model> bestSet = initSet;
        for (int i = 0; i < attrToEx; i++) {
            bestSet = getBestSet(setEachArrayZeros(arrayCloner(bestSet, bestSet.get(0).vector.length)),k);
        }
        return bestSet;
    }

    public ArrayList<Model> getBestSet(ArrayList<ArrayList<Model>> setOfSets, int k) {
        double bestPercent = 0;
        int bestSetIndex = 0;
        double perc;
        System.out.println("Finding best set out of "+setOfSets.size()+" sets");
        for (int i = 0; i < setOfSets.size(); i++) {
            perc = statistics.calcPercentage(k, setOfSets.get(i));
            if (perc > bestPercent) {
                bestPercent = perc;
                bestSetIndex = i;
            }
            System.out.print(i+" ");
        }
        System.out.println("Found! Best perc "+bestPercent+" & bestsetIndex "+ bestSetIndex);
        return setOfSets.get(bestSetIndex);
    }
}
