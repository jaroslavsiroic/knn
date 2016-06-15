import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Knn {
    //todo set, k

    public int k;
    public Map<Model,Double> distances = new HashMap<>();
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


}
