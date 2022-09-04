import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new PartialTrigonometricSequence();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class PartialTrigonometricSequence{
	private int arraySize;
	private ArrayList<Integer> array;
	private int result;
	public PartialTrigonometricSequence() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arraySize = Integer.parseInt(br.readLine());
		array = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<arraySize; i++) {
			array.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(array);
		result = 1;
		for(int i = 0; i<arraySize-1; i++) {
			int resultWhenI = arraySize-i;
			int plusMinAndNextMin = array.get(i)+array.get(i+1);
			int j = arraySize-1;
			while(i+1<j&&plusMinAndNextMin<=array.get(j)) {
				j--;
				resultWhenI--;
			}
			result = Math.max(result, resultWhenI);
		}
		
		System.out.println(result);
	}
	
	public void QuickSort(int s, int e, int[] array) {
		int p = Patition(s,e,array);
		if(s<p-1)QuickSort(s, p-1, array);
		if(p<e)QuickSort(p, e, array);
	}
	
	public int Patition(int s, int e, int[] array) {
		int pivot = array[(s+e)/2];
		while(s<=e) {
			while(array[s]<pivot)s++;
			while(pivot<array[e])e--;
			if(s<=e) {
				Swap(s,e,array);
				s++;
				e--;
			}
		}
		return s;
	}
	
	public void Swap(int ex1, int ex2, int[] array) {
		int tmp = array[ex1];
		array[ex1] = array[ex2];
		array[ex2] = tmp;
	}
}






