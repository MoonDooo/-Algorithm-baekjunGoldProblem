import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Baekjun 1083
 * @author MoonDooo
 */
public class Sort {
	public static void main(String[] args) {
		try {
			new SortArray();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class SortArray{
	private int[] array;
	private int arraySize;
	private int arraySortCounter;
	public SortArray() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arraySize = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		array = new int[arraySize];
		for(int i = 0; i<arraySize; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		arraySortCounter = Integer.parseInt(br.readLine());
		
		for(int i = 0; arraySortCounter>0&& i<arraySize; i++) {
			int maxIndexInPossibleRange = i;
			for(int j = i; j<=i+arraySortCounter&&j<arraySize; j++) {
				if(array[maxIndexInPossibleRange]<array[j]) {
					maxIndexInPossibleRange = j;
				}
			}	
			for(int s = maxIndexInPossibleRange; s > i; s--) {
				swap(s-1, s);	
			}
		}
		for(int i = 0; i<arraySize; i++) {
			System.out.print(array[i]+ " ");
		}
	}
	public void swap(int ArraySmallerElement, int ArrayBiggerElement) {
		arraySortCounter--;
		int tmp = array[ArraySmallerElement];
		array[ArraySmallerElement] = array[ArrayBiggerElement];
		array[ArrayBiggerElement] = tmp;
	}
}






