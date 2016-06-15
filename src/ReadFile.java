import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    public static ArrayList<Model> readFile(){
        double[] mas = new double[Program.nClass];
        ArrayList<Model> modelList = new ArrayList<>();
        int iClass;

        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\knn\\src\\native.txt"))) {
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
                modelList.add(new Model(iClass,mas));
                mas = new double[Program.nClass];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return modelList;
    }
}
