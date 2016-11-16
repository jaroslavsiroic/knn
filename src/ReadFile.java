import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadFile {
    public static final int nClass = 24;
    public static ArrayList<Model> trainingSet = new ArrayList<>(); //70%
    public static ArrayList<Model> testSet = new ArrayList<>(); //30%

    private static int readRecords(String path) {
        int record = 0;

        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader(path)));
            while (s.hasNextLine()) {
                record++;
                s.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            s.close();
        }
        return record;
    }

    public static void readFile(){

        Scanner scanner = new Scanner(System.in);
        String basePath = new File("native.txt").getAbsolutePath();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(basePath));
        } catch (FileNotFoundException e) {
            try {
                basePath = new File("src/native.txt").getAbsolutePath();
                br = new BufferedReader(new FileReader(basePath));
            } catch (FileNotFoundException g) {
                try {
                    System.out.println("Cant find the native.txt file here. Pls spec the full path:");
                    br = new BufferedReader(new FileReader(scanner.next()));
                } catch (FileNotFoundException e1) {
                    System.out.println("File not found. Exit");
                    System.exit(0);
                }
            }
        } finally {
            createSets(br, readRecords(basePath));
        }

        HashMap<Integer, ArrayList<Model>> digitsMap = new HashMap();
        ArrayList<Model> arr = new ArrayList<>();

        arr.addAll(trainingSet);
        arr.addAll(testSet);
        ArrayList<Model> masterSet = Program.clone(arr);

        for (Model model : masterSet) {
            if (!digitsMap.containsKey(model.iClass)) {
                digitsMap.put(model.iClass, new ArrayList<>());
            }
            digitsMap.get(model.iClass).add(model);
        }

        trainingSet.clear();
        testSet.clear();

        int divider;
        for (Integer i : digitsMap.keySet()) {

            divider = (digitsMap.get(i).size() * 70) / 100; //dividing index

            for (int j = 0; j < digitsMap.get(i).size(); j++) {
                if (j < divider) {
                    trainingSet.add(digitsMap.get(i).get(j));
                } else {
                    testSet.add(digitsMap.get(i).get(j));
                }
            }
        }

    }

    private static void createSets(BufferedReader br, int records) {
        double[] mas = new double[nClass];
        int iClass;
        String str, string[];

        try {
            for (int i = 0; i < records; i++) {
                str = br.readLine();
                string = str.split("\t");
                iClass = Integer.parseInt(string[0]);
                for (int j = 1; j < nClass + 1; j++) {
                    try {
                        mas[j - 1] = Double.parseDouble(string[j]);
                    } catch (NumberFormatException exc) {
                        System.out.println("ERROR PARSING DOUBLE");
                    }
                }
                if (i >= records * 0.7) {
                    testSet.add(new Model(iClass, mas));
                } else trainingSet.add(new Model(iClass, mas));
                mas = new double[nClass];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


