import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new SameRemainder();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class SameRemainder {
	private ArrayList<Integer> array;
	private HashSet<Integer> differenceSet;
	private int result;
	public SameRemainder() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		array = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<num; i++) {
			array.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(array,Collections.reverseOrder());
		
		differenceSet = new HashSet<>();
		for(int i = 1; i<num; i++) {
			int difference = array.get(0)-array.get(i);
			if(difference!=0) {
				differenceSet.add(difference);
			}
		}

		Iterator<Integer> it = differenceSet.iterator();
		result = it.next();
		while(it.hasNext()){
			result = GreatestCommonDivisor(it.next(), result);
		}
		System.out.println(result);
	}
	
	public int GreatestCommonDivisor(int a, int b) {
		if(a<b) {
			int tmp = a;
			a= b;
			b= tmp;
		}
	
		if(a==0&&b==0) {
			return Integer.MAX_VALUE;
		}
		else if(b==0) {
			return a;
		}
		
		int r;
		if(a%b != 0) {
			r  = GreatestCommonDivisor(b, a%b); 
		}else {
			return b;
		}
		
		return r;
	}
}
