import java.util.*;

public class Knn {
    private int k;
    private Model uModel;
    ArrayList<Model> set;

    public int init(ArrayList<Model> set, Model uModel, int k){
        this.set = set;
        this.uModel = uModel;
        this.k = k;

        for(Model m : set){
            m.distance = distance(m); // calc distance
        }
        Collections.sort(set, (o1, o2) -> Double.compare(o1.distance, o2.distance)); //sort set by distance

        return determineClass();
    }
    private double distance(Model model){
        double distance=0;
        for(int i=0; i < model.vector.length; i++){
            distance+=Math.pow(model.vector[i]-uModel.vector[i],2);
        }
        distance=Math.sqrt(distance);
        return distance;
    }

    private ArrayList<Model> getNearest(){
        int i = 0;
        ArrayList<Model> nearestObjects = new ArrayList<Model>();
        for (Model model : set) {
            nearestObjects.add(model);
            i++;
            if (i >= k) break;
        }
        return nearestObjects;
    }

    private int determineClass(){
        Map<Integer, Integer> map = new HashMap<>();

        for (Model m : getNearest()) {
            Integer val = map.get(m.iClass);
            map.put(m.iClass, val == null ? 1 : val + 1);
        }

        Map.Entry<Integer, Integer> max = null;

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();
    }
}