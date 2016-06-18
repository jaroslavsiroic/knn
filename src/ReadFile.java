import java.io.*;
import java.util.ArrayList;

public class ReadFile {
    public static ArrayList<Model> trainingSet = new ArrayList<>(); //70%
    public static ArrayList<Model> testSet = new ArrayList<>(); //30%

    public static void readFile(){
        double[] mas = new double[Program.nClass];
        int iClass;



        String basePath = new File("src\\native.txt").getAbsolutePath();
        try(BufferedReader br = new BufferedReader(new FileReader(basePath))) {
            StringBuilder sb = new StringBuilder();
            String str, string[];

            for (int i = 0; i < Program.nRecords; i++) {
                str = br.readLine();
                string = str.split("\t");
                iClass = Integer.parseInt(string[0]);
                for (int j = 1; j < Program.nClass+1; j++) {
                    try {
                        mas[j-1] = Double.parseDouble(string[j]);
                    }catch (NumberFormatException exc){
                        System.out.println("ERROR PARSING DOUBLE");
                    }
                }
                if(i>=7000) testSet.add(new Model(iClass,mas));
                else trainingSet.add(new Model(iClass,mas));
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


        String basePath = new File("src\\native.txt").getAbsolutePath();
        try(BufferedReader br = new BufferedReader(new FileReader(basePath))) {