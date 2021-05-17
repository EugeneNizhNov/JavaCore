package JavaCore.Streams.Task1;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        Integer[] arrayTemp = mySort(intList.toArray(new Integer[0]));
        for (int i = 0; i < arrayTemp.length; i++) {
            if (arrayTemp[i] > 0) {
                if (arrayTemp[i] % 2 == 0) {
                    System.out.println(arrayTemp[i]);
                }
            }
        }
    }

    public static Integer[] mySort(Integer[] intString) {
        for (int left = 0; left < intString.length; left++) {
            int value = intString[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < intString[i]) {
                    intString[i + 1] = intString[i];

                } else {
                    break;
                }
            }
            intString[i + 1] = value;
        }
        return intString;
    }
}
