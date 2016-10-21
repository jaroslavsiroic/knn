import java.util.ArrayList;

/**
 * Created by kosciuszko on 16.10.21.
 */
public class AtributeTester {







    public ArrayList<Model> setCharacteristicToZero(ArrayList<Model> args, int charcteristic) {

        ArrayList<Model> arrayList = new ArrayList<>();

        if (args.get(0).vector[charcteristic] != 0) {
            for (Model model : arrayList) {
                model.vector[charcteristic] = 0;
            }

        }
        return arrayList;
    }
}
