import java.util.ArrayList;
import java.util.Arrays;

public class Model implements Comparable{
    public int iClass;
    public double[] vector;
    public double distance;
    public ArrayList<Integer> excludedIndexes;



    public Model(int iClass, double[] vector) {
        this.iClass = iClass;
        this.vector = vector;
        this.excludedIndexes = new ArrayList<>();
    }

    public Model(int iClass, double[] vector, ArrayList<Integer> excludedIndexes) {
        this.iClass = iClass;
        this.vector = vector;
        this.excludedIndexes = excludedIndexes;
    }

    @Override
    public int compareTo(Object o) {
        Model m = (Model) o;
        return m.distance > this.distance ? 1 : 0;
    }

    public Model clone() {
        ArrayList<Integer> newList = new ArrayList<>();
        newList.addAll(this.excludedIndexes);
        Model m = new Model(this.iClass, this.vector.clone(), newList);
        return m;
    }

    @Override
    public String toString() {
        return "Model{" +
                "iClass=" + iClass +
                ", vector=" + Arrays.toString(vector) +
                ", distance=" + distance +
                ", excludedIndexes=" + excludedIndexes +
                '}';
    }
}


