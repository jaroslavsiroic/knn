import java.util.HashMap;
import java.util.Map;

public class Knn {
    //todo set, k

    public int k;
    public Map<Model,Double> distances = new HashMap<>();

    private Model uModel;

    public double distance(Model model){
        double distance=0;
        //double sum = 0;
        for(int i=0; i < uModel.vector.length; i++){
            distance+=Math.pow(uModel.vector[i]-model.vector[i],2);
        }
        distance=Math.sqrt(distance);
        return distance;
    }



}
