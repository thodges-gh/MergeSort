/**
 * Created by Thomas Hodges on 4/4/2017.
 * MergeSort.java
 *
 * Original Algorithm Author: David Kosbie
 * http://kosbie.net/cmu/summer-08/15-100/handouts/IterativeMergeSort.java
 */
public class MergeSort implements SortInterface {

    int count = 0;
    long timeStart = 0;
    long timeEnd = 0;

    /////////////////////////////////////////
    // Iterative mergeSort
    /////////////////////////////////////////

    public int[] iterativeSort(int[] a) throws UnsortedException {
        count++;
        timeStart = System.nanoTime();
        int[] aux = new int[a.length];
        for (int blockSize = 1; blockSize < a.length; blockSize *= 2) {
            count++;
            for (int start = 0; start < a.length; start += 2 * blockSize) {
                merge(a, aux, start, start + blockSize, start + 2 * blockSize);
            }
        }

        timeEnd = System.nanoTime();
        checkSortedArray(aux);
        return aux;
    }

    /////////////////////////////////////////
    // Recursive mergeSort, adapted from:
    // Sedgewick and Wayne, Introduction to Programming in Java
    // http://www.cs.princeton.edu/introcs/42sort/MergeSort.java.html
    /////////////////////////////////////////

    private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        count++;
        // DK: add two tests to first verify "mid" and "hi" are in range
        if (mid >= a.length) {
            count++;
            return;
        }

        if (hi > a.length) {
            count++;
            hi = a.length;
        }

        int i = lo, j = mid;

        for (int k = lo; k < hi; k++) {
            count++;
            if (i == mid) {
                count++;
                aux[k] = a[j++];
            }
            else if (j == hi) {
                count++;
                aux[k] = a[i++];
            }
            else if (a[j] < a[i]) {
                count++;
                aux[k] = a[j++];
            }
            else {
                count++;
                aux[k] = a[i++];
            }
        }

        // copy back
        for (int k = lo; k < hi; k++) {
            count++;
            a[k] = aux[k];
        }
    }

    private void recursiveSort(int[] a, int[] aux, int lo, int hi) {
        count++;
        // base case
        if (hi - lo <= 1) {
            count++;
            return;
        }

        // sort each half, recursively
        int mid = lo + (hi - lo) / 2;

        recursiveSort(a, aux, lo, mid);

        recursiveSort(a, aux, mid, hi);

        // merge back together
        merge(a, aux, lo, mid, hi);
    }

    public int[] recursiveSort(int[] a) throws UnsortedException {
        count++;
        timeStart = System.nanoTime();
        int n = a.length;

        int[] aux = new int[n];

        recursiveSort(a, aux, 0, n);

        timeEnd = System.nanoTime();
        checkSortedArray(aux);

        return aux;
    }

    public int getCount() {
        int result = count;
        count = 0;
        return result;
    }

    public long getTime() {
        long time = timeEnd - timeStart;
        timeEnd = 0;
        timeStart = 0;
        return time;
    }

    private void checkSortedArray(int[] list) throws UnsortedException {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] > list[i + 1]) {
                for (int j = 0; i < list.length - 1; j++) {
                    System.out.println(" " + list[j]);
                }
                throw new UnsortedException("The array was not sorted correctly: \n" +
                        list[i] + " at index " + i + " and " + list[i + 1] + " at index " + (i + 1));
            }
        }
    }

}