import java.lang.reflect.Array;

import static java.lang.reflect.Array.getLength;

public class ArrayUtils {
    private static Object remove(Object array, int index) {
        int length = getLength(array);
        if (index < 0 || index >= length) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }

        Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
        System.arraycopy(array, 0, result, 0, index);
        if (index < length - 1) {
            System.arraycopy(array, index + 1, result, index, length - index - 1);
        }

        return result;
    }
    public static double[] remove(double[] array, int index) {
        return (double[]) remove((Object) array, index);
    }
}
