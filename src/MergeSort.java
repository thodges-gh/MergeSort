/**
 * Created by Thomas Hodges on 4/4/2017.
 * MergeSort.java
 *
 * Original Algorithm Author: David Kosbie
 * http://kosbie.net/cmu/summer-08/15-100/handouts/IterativeMergeSort.java
 */
public class MergeSort implements SortInterface {

    int[] array;

    /////////////////////////////////////////
    // Iterative mergeSort
    /////////////////////////////////////////

    public int[] iterativeSort(int[] a) {
        int[] aux = new int[a.length];
        for (int blockSize = 1; blockSize < a.length; blockSize *= 2)
            for (int start = 0; start < a.length; start += 2 * blockSize)
                merge(a, aux, start, start + blockSize, start + 2 * blockSize);
        return aux;
    }

    /////////////////////////////////////////
    // Recursive mergeSort, adapted from:
    // Sedgewick and Wayne, Introduction to Programming in Java
    // http://www.cs.princeton.edu/introcs/42sort/MergeSort.java.html
    /////////////////////////////////////////

    private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        // DK: add two tests to first verify "mid" and "hi" are in range
        if (mid >= a.length) return;
        if (hi > a.length) hi = a.length;
        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if (i == mid) aux[k] = a[j++];
            else if (j == hi) aux[k] = a[i++];
            else if (a[j] < a[i]) aux[k] = a[j++];
            else aux[k] = a[i++];
        }
        // copy back
        for (int k = lo; k < hi; k++)
            a[k] = aux[k];
    }

    private void recursiveSort(int[] a, int[] aux, int lo, int hi) {
        // base case
        if (hi - lo <= 1) return;
        // sort each half, recursively
        int mid = lo + (hi - lo) / 2;
        recursiveSort(a, aux, lo, mid);
        recursiveSort(a, aux, mid, hi);
        // merge back together
        merge(a, aux, lo, mid, hi);
    }

    public int[] recursiveSort(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        recursiveSort(a, aux, 0, n);
        return aux;
    }

    public int getCount() {
        return 0;
    }

    public long getTime() {
        return 0;
    }

}