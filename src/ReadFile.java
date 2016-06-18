import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    public static ArrayList<Model> trainingSet = new ArrayList<>(); //70%
    public static ArrayList<Model> testSet = new ArrayList<>(); //30%

    public static void readFile(){
        Scanner scanner = new Scanner(System.in);
        String basePath = new File("native.txt").getAbsolutePath();
        double[] mas = new double[Program.nClass];
        int iClass;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(basePath));
        } catch (FileNotFoundException e) {
            try {
                basePath = new File("src\\native.txt").getAbsolutePath();
                br = new BufferedReader(new FileReader(basePath));
            } catch (FileNotFoundException g) {
                try {
                    System.out.println("Cant find the native.txt file here. Pls spec the full path:");
                    br = new BufferedReader(new FileReader(scanner.next()));
                } catch (FileNotFoundException e1) {
                    System.out.println("File not found. Exit");
                }
            }
        }

        try{
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
