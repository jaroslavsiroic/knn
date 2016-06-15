import java.util.*;

public class Knn {
    //todo set, k

    public int k;
    public TreeMap<Model,Double> distances = new TreeMap<>();
    private Model uModel;
    private ArrayList<Model> set;

    public Knn(ArrayList<Model> set, Model uModel, int k) {
        this.set = set;
        this.uModel = uModel;
        this.k = k;
    }
    public int init(){

        return 0;
    }
    private double distance(Model model){
        double distance=0;
        //double sum = 0;
        for(int i=0; i < model.vector.length; i++){
            distance+=Math.pow(model.vector[i]-uModel.vector[i],2);
        }
        distance=Math.sqrt(distance);
        return distance;
    }

    public void sortHashMap(){
        distances = (TreeMap<Model, Double>) (entriesSortedByValues(distances));
    }
    private <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    void allDistances() {

        for (Model model : set) {
            distances.put(model, distance(model));
        }

    }

    ArrayList<Model> getNearest() {
        int i = 0;
        ArrayList<Model> nearestObjects = new ArrayList<Model>();
        /*
        for (Model model : distances) {
            nearestObjects.add(model);
            i++;
            if (i >= k) break;
        }
        */
        return nearestObjects;
    }
}