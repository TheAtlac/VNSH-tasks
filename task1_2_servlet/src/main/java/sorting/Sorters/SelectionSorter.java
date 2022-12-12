package sorting.Sorters;

public class SelectionSorter implements Sorter {
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
        for (int i = 0; i < (len-1); ++i) {
            int loc_min_ind = i;
            for (int j = i+1; j < len; ++j) {
                if (copy[j] < copy[loc_min_ind]) {
                    loc_min_ind = j;
                }
            }
            int temp = copy[i];
            copy[i] = copy[loc_min_ind];
            copy[loc_min_ind] = temp;
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
