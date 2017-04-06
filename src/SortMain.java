import java.util.stream.IntStream;

/**
 * Created by Thomas Hodges on 4/4/2017.
 * SortMain.java
 */
public class SortMain {

    public static void main(String[] args) throws Exception {
        int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000} ;
        BenchmarkSorts benchmarkSorts = new BenchmarkSorts(sizes);
//        int[] unsortedArray = {5, 2, 9, 3, 5, 2, 1, 0};
//        SortInterface sortInterface = new MergeSort();
//        int[] sortedArrayIterative = sortInterface.iterativeSort(unsortedArray);
//        int[] sortedArrayRecursive = sortInterface.recursiveSort(unsortedArray);
//        System.out.println("Iterative\n");
//        IntStream.range(0, sortedArrayIterative.length)
//                 .forEach(a -> System.out.print(sortedArrayIterative[a] + " "));
//        System.out.println("\nRecursive\n");
//        IntStream.range(0, sortedArrayRecursive.length)
//                .forEach(a -> System.out.print(sortedArrayRecursive[a] + " "));
    }
}
