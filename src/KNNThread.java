import java.util.ArrayList;

public class KNNThread extends Thread {
    //working with lib
    private Statistics stat;

    //input
    private ArrayList<Model> set;
    private int k;
    private int setIndex;

    //output
    private double percentage;


    public KNNThread(String name) {
        super(name);
        stat = new Statistics();
    }

    public void init(ArrayList<Model> set, int k, int setIndex) {
        this.set = set;
        this.k = k;
        this.setIndex = setIndex;
    }

    @Override
    public void run() {
        if (set == null) {
            System.out.println(this.getName() + " refuse to work... ending");
            percentage = 0;
            return;
        }
        System.out.println(this.getName() + " fired!");
        percentage = stat.calcPercentage(k, set);
    }


    public double getPercentage() {
        return percentage;
    }

    public int getSetIndex() {
        return setIndex;
    }
}
