import java.util.stream.IntStream;

/**
 * Created by Thomas Hodges on 4/4/2017.
 * SortMain.java
 */
public class SortMain {

    public static void main(String[] args) throws Exception {
        int[] sizes = {50, 100, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000};
        BenchmarkSorts benchmarkSorts = new BenchmarkSorts(sizes);
    }
}
