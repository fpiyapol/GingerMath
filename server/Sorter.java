import java.util.Arrays;
import java.util.Comparator;

public class Sorter {
	public void sort(Data[] data) {
		Arrays.sort(data, new Comparator<Data>() {
		    public int compare(Data a, Data b) {
		        return a.compareTo(b);
		    }
		});
	}
}
