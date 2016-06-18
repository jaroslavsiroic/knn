import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    public static ArrayList<Model> trainingSet = new ArrayList<>(); //70%
    public static ArrayList<Model> testSet = new ArrayList<>(); //30%

    public static int readRecords() {
        int record = 0;
        String basePath = new File("src\\native.txt").getAbsolutePath();
        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader(basePath)));
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
        double[] mas = new double[Program.nClass];
        int iClass;
        int records=readRecords();
        System.out.println(records);


        String basePath = new File("src\\native.txt").getAbsolutePath();
        try (BufferedReader br = new BufferedReader(new FileReader(basePath))) {
            StringBuilder sb = new StringBuilder();
            String str, string[];

            for (int i = 0; i < records; i++) {
                str = br.readLine();
                string = str.split("\t");
                iClass = Integer.parseInt(string[0]);
                for (int j = 1; j < Program.nClass + 1; j++) {
                    try {
                        mas[j - 1] = Double.parseDouble(string[j]);
                    } catch (NumberFormatException exc) {
                        System.out.println("ERROR PARSING DOUBLE");
                    }
                }
                if (i >= records * 0.7) {
                    testSet.add(new Model(iClass, mas));
                } else trainingSet.add(new Model(iClass, mas));
                mas = new double[Program.nClass];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}


