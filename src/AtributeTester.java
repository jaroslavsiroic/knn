import java.util.ArrayList;

public class AtributeTester {
    private Statistics statistics = new Statistics();

    //non-zero elem quantity of array (attr vector)
    public int nonZeroQ(double[] arr) {
        int q = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                q++;
            }
        }
        return q;
    }
    public void setArrayZeros(ArrayList<Model> set, int attrIndex) {
        for (Model model : set) {
            model.vector[attrIndex] = 0;
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
        double[] vector = setOfSets.get(0).get(0).vector;
        for (int i = 0; i < setOfSets.size(); i++) {
            for (int j = 0; j < vector.length; j++) {
                if (setOfSets.get(i).get(0).vector[j] != 0) {
                    setArrayZeros(setOfSets.get(i), j);
                    break;
                }
            }
        }
        return setOfSets;
    }


    // I want to exclude attrToEx (ec. 2) attr and find best attributes
    public ArrayList<Integer> getBestAttr(ArrayList<Model> initSet, int k, int attrToEx) {
        //so lets do it
        //first, we need the result array (best index array)
        ArrayList<Integer>  bestIndexArr = new ArrayList<>();
        //now lets get best of the best arrayList
        ArrayList<Model> bestSet = getBestOfTheBest(initSet, k, attrToEx);
        double[] bestVector = bestSet.get(0).vector;
        for (int i = 0; i < bestVector.length; i++) {
            if (bestVector[i] != 0) {
                bestIndexArr.add(i);
            }
        }

        return bestIndexArr;
    }
    // magical method here
    public ArrayList<Model> getBestOfTheBest(ArrayList<Model> initSet, int k, int attrToEx) {
        //to get best set, we need to create a bunch of clone sets here, and iterate throw this process attrToEx (ec. 2) times
        ArrayList<Model> bestSet = initSet;
        for (int i = 0; i < attrToEx; i++) {
            bestSet = getBestSet(setEachArrayZeros(arrayCloner(bestSet, nonZeroQ(bestSet.get(0).vector))),k);
        }
        return bestSet;
    }



//
//
//
//
//    public ArrayList<Integer> getBestCharateristics(ArrayList<Model> initSet, int k, int numberOfCharacteristics) {
//        ArrayList<Integer> indexesOfBestcharacteristics = new ArrayList<>();
//        ArrayList<Model> setWithExcludedCharacteristicsget = getOptimizedSet(initSet, k, numberOfCharacteristics);
//        Model model = setWithExcludedCharacteristicsget.get(0);
//        for (int i = 0; i < model.vector.length; i++) {
//            if (model.vector[i] != 0) {
//                indexesOfBestcharacteristics.add(i);
//            }
//        }
//        return indexesOfBestcharacteristics;
//    }
//
//    public ArrayList<Model> getOptimizedSet(ArrayList<Model> initSet, int k, int numberOfCharacteristics) {
//
//        int excludedCharacteristics = initSet.get(0).vector.length - numberOfCharacteristics;
//        ArrayList<Model> optimizedSet = new ArrayList<>();
//        for (int i = 0; i < excludedCharacteristics; i++) {
//
//            optimizedSet = getBestSet(createArraySet(initSet), k);
//        }
//        return optimizedSet;
//    }
//
//    public ArrayList<ArrayList<Model>> createArraySet(ArrayList<Model> args) {
//        ArrayList<ArrayList<Model>> arraySet = new ArrayList<>();
//        ArrayList<Model> list;
//        for (int i = 0; i < args.get(0).vector.length; i++) {
//            list = setCharacteristicToZero(args, i);
//            if (!list.isEmpty()) {
//                arraySet.add(list);
//            }
//        }
//        return arraySet;
//    }
//
//    public ArrayList<Model> setCharacteristicToZero(ArrayList<Model> args, int charcteristic) {
//        ArrayList<Model> arrayList = new ArrayList<>();
//        if (args.get(0).vector[charcteristic] != 0) {
//            for (Model model : args) {
//                arrayList.add(model.clone());
//            }
//            for (Model model : arrayList) {
//                model.vector[charcteristic] = 0;
//            }
//        }
//        return arrayList;
//    }

    public ArrayList<Model> getBestSet(ArrayList<ArrayList<Model>> setOfSets, int k) {
        System.out.println("setOfSets.size() -> "+ setOfSets.size());
        for (int i = 0; i < setOfSets.get(0).get(0).vector.length; i++) {
            System.out.println(setOfSets.get(0).get(0).vector[i]);
        }
        double bestPercent = 0;
        int bestSetIndex = 0;
        double perc;
        for (int i = 0; i < setOfSets.size(); i++) {
            perc = statistics.calcPercentage(k, setOfSets.get(i));
            if (perc > bestPercent) {
                bestPercent = perc;
                bestSetIndex = i;
            }
            System.out.println("perc "+perc);
            System.out.println("best perc "+bestPercent+" & bestsetIndex "+ bestSetIndex);
        }
        return setOfSets.get(bestSetIndex);
    }
}
