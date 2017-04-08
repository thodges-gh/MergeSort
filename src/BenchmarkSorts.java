import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Thomas Hodges on 4/4/2017.
 * BenchmarkSorts.java
 */
public class BenchmarkSorts {

    private final int NUMBER_OF_RUNS = 50;

    int[] array;
    int[] sortedIterativeArray;
    int[] sortedRecursiveArray;
    int iterativeCount = 0;
    int recursiveCount = 0;
    int iterativeIndex = 0;
    int recursiveIndex = 0;
    long iterativeTime, recursiveTime;
    int [] iterativeCountLog = new int [50];
    int [] recursiveCountLog = new int [50];
    long [] iterativeTimeLog = new long[50];
    long []recursiveTimeLog = new long[50];

    MergeSort mergeSort = new MergeSort();

    public BenchmarkSorts(int[] sizes) {
        IntStream.range(0, sizes.length).forEach(i -> new BenchmarkSorts(sizes[i]));
    }

    private BenchmarkSorts(int n) {
        IntStream.range(0, NUMBER_OF_RUNS).forEach(i -> {
            array = new int[n];
            IntStream.range(0, n).forEach(j -> {
                Random random = new Random();
                array[j] = random.nextInt(1000);
            });
            try {
                runSorts();
            } catch (UnsortedException e) {
                System.out.println(e.getMessage());
            }
        });
        displayReport(n);
    }


    public void runSorts() throws UnsortedException {

        sortedIterativeArray = mergeSort.iterativeSort(array);
        int returnCount = mergeSort.getCount();
        long returnTime = mergeSort.getTime();
        iterativeCount = iterativeCount + returnCount;
        iterativeTime = iterativeTime + returnTime;
        iterativeCountLog[iterativeIndex] = returnCount;
        iterativeTimeLog[iterativeIndex] = returnTime;
        iterativeIndex++;

        sortedRecursiveArray = mergeSort.recursiveSort(array);
        returnCount = mergeSort.getCount();
        returnTime = mergeSort.getTime();
        recursiveCount = recursiveCount + returnCount;
        recursiveTime = recursiveTime + returnTime;
        recursiveCountLog[recursiveIndex] = recursiveCount;
        recursiveTimeLog[recursiveIndex] = recursiveTime;
        recursiveIndex++;
    }

    public void displayReport(int n) {
        double iterativeAverageCount, iterativeAverageTime, recursiveAverageCount, recursiveAverageTime;
        double iterativeSDCount = 0;
        double iterativeSDTime = 0;
        double recursiveSDCount = 0;
        double recursiveSDTime = 0;

        iterativeAverageCount = iterativeCount / 49;
        iterativeAverageTime = iterativeTime / 49;
        recursiveAverageCount = recursiveCount / 49;
        recursiveAverageTime = recursiveTime / 49;

        for (int i = 1; i < NUMBER_OF_RUNS; i++){
            iterativeSDCount = iterativeSDCount + Math.pow((iterativeCountLog[i] - iterativeAverageCount), 2);
            iterativeSDTime = iterativeSDTime + Math.pow((iterativeTimeLog[i] - iterativeAverageTime), 2);
            recursiveSDCount = recursiveSDCount + Math.pow((recursiveCountLog[i] - recursiveAverageCount), 2);
            recursiveSDTime = recursiveSDTime + Math.pow((recursiveTimeLog[i] - recursiveAverageTime), 2);
        }

        iterativeSDCount = Math.pow(iterativeSDCount, .5) / n;
        iterativeSDTime = Math.pow(iterativeSDTime, .5) / n;
        recursiveSDCount = Math.pow(recursiveSDCount, .5) / n;
        recursiveSDTime = Math.pow(recursiveSDTime, .5) / n;

        System.out.println("Data Set Size (n): " + n +
//                    "\nIterative array: " + Arrays.toString(sortedIterativeArray) +
//                    "\nRecursive array: " + Arrays.toString(sortedRecursiveArray) +
                    "\n\tIterative Selection Sort Results: \t\t\t\t\tRecursive Selection Sort Results:" +
                    "\n\tAverage Critical Operation Count: " + Math.round(iterativeAverageCount) +
                        "\t\t\tAverage Critical Operation Count: " + Math.round(recursiveAverageCount) +
                    "\n\tStandard Deviation of Count: " + Math.round(iterativeSDCount) +
                        "\t\t\t\t\tStandard Deviation of Count: " + Math.round(recursiveSDCount) +
                    "\n\tAverage Execution Time: " + Math.round(iterativeAverageTime) +
                        "\t\t\t\t\t\tAverage Execution Time: " + Math.round(recursiveAverageTime) +
                    "\n\tStandard Deviation of Time: " + Math.round(iterativeSDTime) +
                        "\t\t\t\t\t\tStandard Deviation of Time: " + Math.round(recursiveSDTime));
    }
}
