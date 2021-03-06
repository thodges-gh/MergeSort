/**
 * Created by Thomas Hodges on 4/4/2017.
 * SortInterface.java
 */
public interface SortInterface {

    int[] iterativeSort(int[] array) throws UnsortedException;
    int[] recursiveSort(int[] array) throws UnsortedException;

    int getCount();
    long getTime();
}
