import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Thomas Hodges on 4/4/2017.
 * BenchmarkSorts.java
 */
public class BenchmarkSorts {

    private final int NUMBER_OF_RUNS = 50;

    int[] array;
    int iterativeCount = 0;
    int recursiveCount = 0;
    long iterativeTime, recursiveTime;
    int [] iterativeCountLog = new int [50];
    int [] recursiveCountLog = new int [50];
    int a = 0;
    int b = 0;
    long [] iterativeTimeLog = new long[50];
    long []recursiveTimeLog = new long[50];
    int n;

    MergeSort mergeSort = new MergeSort();

    public BenchmarkSorts(int[] sizes) {
        IntStream.range(0, sizes.length).forEach(i -> new BenchmarkSorts(sizes[i]));
    }

    private BenchmarkSorts(int n) {
        IntStream.range(0, NUMBER_OF_RUNS).forEach(i -> {
            array = new int[n];
            IntStream.range(0, n).forEach(j -> array[j] = new Random().nextInt(1001));
            try {
                runSorts();
            } catch (UnsortedException e) {
                System.out.println(e.getMessage());
            }
        });
        displayReport(n);
    }


    public void runSorts() throws UnsortedException {

        int[] sortedIterativeArray = mergeSort.iterativeSort(array);
        int returnCount = mergeSort.getCount();
        long returnTime = mergeSort.getTime();
        iterativeCount = iterativeCount + returnCount;
        iterativeTime = iterativeTime + returnTime;
        iterativeCountLog[a] = returnCount;
        iterativeTimeLog[a] = returnTime;
        a++;

        int[] sortedRecursiveArray = mergeSort.recursiveSort(array);
        returnCount = mergeSort.getCount();
        returnTime = mergeSort.getTime();
        recursiveCount = recursiveCount + returnCount;
        recursiveTime = recursiveTime + returnTime;
        recursiveCountLog[b] = recursiveCount;
        recursiveTimeLog[b] = recursiveTime;
        b++;
    }

    public void displayReport(int n) {
        double iterativeAverageCount = 0;
        double iterativeAverageTime = 0;
        double recursiveAverageCount = 0;
        double recursiveAverageTime = 0;
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

        System.out.println("Iterative Selection Sort Results: " + "\nData Set Size (n): " + n +
                ", Average Critical Operation Count: " + iterativeAverageCount + ", Standard Deviation of Count: " +
                iterativeSDCount + ", Average Execution Time: " + iterativeAverageTime + ", Standard Deviation of Time: " +
                iterativeSDTime);

        System.out.println("Recursive Selection Sort Results: " + "\nData Set Size (n): " + n +
                ", Average Critical Operation Count: " + recursiveAverageCount + ", Standard Deviation of Count: " +
                recursiveSDCount + ", Average Execution Time: " + recursiveAverageTime + ", Standard Deviation of Time: " +
                recursiveSDTime);
    }
}
