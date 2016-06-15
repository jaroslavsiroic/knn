import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    public static ArrayList<Model> learningSet = new ArrayList<>(); //70%
    public static ArrayList<Model> trainingSet = new ArrayList<>(); //30%

    public static void readFile(){
        double[] mas = new double[Program.nClass];
        int iClass;

        String ajsurl = "C:\\Users\\User\\IdeaProjects\\knn\\src\\native.txt";
        String markurl = "D:\\Knn\\knn\\src\\native.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(ajsurl))) {
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
                if(i>=7000) trainingSet.add(new Model(iClass,mas));
                else learningSet.add(new Model(iClass,mas));
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
