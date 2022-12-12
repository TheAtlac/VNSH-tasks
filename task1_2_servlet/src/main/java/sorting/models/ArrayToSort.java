package sorting.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sorting.Sorters.BogoSorter;
import sorting.Sorters.BubbleSorter;
import sorting.Sorters.SelectionSorter;

import java.util.Objects;

@JsonIgnoreProperties(value = {"algorithm"}, allowSetters= true, ignoreUnknown = true)
public class ArrayToSort {
//    @JsonIgnore
    @JsonProperty("algorithm")
    private String algorithm;
    @JsonProperty("time")
    private long time;

    @JsonProperty("values")
    private int[] values;


    public ArrayToSort() {
        this.algorithm = "bubble";
        this.values = new int[] {};
        this.time = 0;
    }

    public ArrayToSort(String algorithm, int[] values) {
        this.algorithm = algorithm;
        long start = System.nanoTime();
        if (Objects.equals(algorithm, "bubble")) {
            BubbleSorter sorter = new BubbleSorter();
            this.values = sorter.sort(values);
            long finish = System.nanoTime();
            this.time = (finish - start) / 1000;
        } else if (Objects.equals(algorithm, "selection")) {
            SelectionSorter sorter = new SelectionSorter();
            long finish = System.nanoTime();
            this.time = (finish - start) / 1000;
            this.values = sorter.sort(values);
        } else if (Objects.equals(algorithm, "bogo")) {
            BogoSorter sorter = new BogoSorter();
            long finish = System.nanoTime();
            this.time = (finish - start) / 1000;
            this.values = sorter.sort(values);
        }

    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        if (values.length != 0) {
            if (Objects.equals(algorithm, "bubble")) {
                BubbleSorter sorter = new BubbleSorter();
                long start = System.nanoTime();
                values = sorter.sort(values);
                long finish = System.nanoTime();
                this.values = values;
                this.time = (finish - start) / 1000;
            } else if (Objects.equals(algorithm, "selection")) {
                SelectionSorter sorter = new SelectionSorter();
                long start = System.nanoTime();
                values = sorter.sort(values);
                long finish = System.nanoTime();
                this.values = values;
                this.time = (finish - start) / 1000;
            } else if (Objects.equals(algorithm, "bogo")) {
                BogoSorter sorter = new BogoSorter();
                long start = System.nanoTime();
                values = sorter.sort(values);
                long finish = System.nanoTime();
                this.values = values;
                this.time = (finish - start) / 1000;
            }
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "{" +
                "time: 100, " +
                "algorithm='" + algorithm + '\'' +
                ", values=" + values.toString() +
                '}';
    }
}
