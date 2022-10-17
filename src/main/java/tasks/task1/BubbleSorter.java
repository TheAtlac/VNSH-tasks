package tasks.task1;

public class BubbleSorter implements Sorter {
    @Override
    public int[] sort(int[] numbers) {
        if (numbers == null) {
            throw new NullPointerException("Array is null");
        }
        if (numbers.length == 0) {
            return new int[] {};
        }
        int len = numbers.length;
        int[] copy = copyArray(numbers);
        boolean flag = false, flag2 = false;
        while (!flag2) {
            for (int i = 0; i < (len - 1); ++i) {
                if (copy[i] > copy[i + 1]) {
                    flag = false;
                    int temp = copy[i];
                    copy[i] = copy[i + 1];
                    copy[i + 1] = temp;
                }
            }
            if (flag) flag2 = true;
            else flag = true;
        }
        return copy;
    }
    public static int[] copyArray(int[] array) {
        int n = array.length;
        int[] copy = new int[n];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }
}
