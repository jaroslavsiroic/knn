import java.util.ArrayList;

public class ThreadController {

    private KNNThread[] threads;

    public ThreadController(int threadCount) {
        initThreads(threadCount);
    }

    private void initThreads(int threadCount) {
        threads = new KNNThread[threadCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new KNNThread("#"+i+" KNNThread");
        }
    }

    public void initThread(ArrayList<Model> set, int setIndex, int k, int threadIndex) {
        if (threadIndex < 0 || threadIndex >= threads.length) {
            return; // in case you try to mess up
        }
        threads[threadIndex].init(set, k, setIndex);
    }

    public Returner bestPercentInUnderTake() { // returns index value

        double bestPercent = 0;
        int bestPercentIndex = 0;

        for (KNNThread thread : threads) {
            thread.start();
        }

        try {
            for (KNNThread thread : threads) {
                thread.join();
                if (thread.getPercentage() > bestPercent) {
                    bestPercent = thread.getPercentage();
                    bestPercentIndex = thread.getSetIndex();
                }
            }
            System.out.println("All threads joined... undertake done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initThreads(threads.length);
        return new Returner(bestPercent, bestPercentIndex);
    }
}
