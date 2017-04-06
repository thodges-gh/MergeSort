/**
 * Created by Thomas Hodges on 4/4/2017.
 * SortInterface.java
 */
public interface SortInterface {

    int[] iterativeSort(int[] array) throws Exception;
    int[] recursiveSort(int[] array) throws Exception;

    public int getCount();
    public long getTime();
}
