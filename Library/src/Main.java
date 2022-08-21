import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new Library();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class Library{
	private int books;
	private int booksThatCanLift;
	private ArrayList<Integer> plus;
	private ArrayList<Integer> minus;
	private long result;
	public Library() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		books = Integer.parseInt(st.nextToken());
		booksThatCanLift = Integer.parseInt(st.nextToken());
		
		plus= new ArrayList<>();
		minus= new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<books; i++) {
			int input = Integer.parseInt(st.nextToken());
			if(input<0) {
				minus.add(Math.abs(input));
			}  else {
				plus.add(input);
			}
		}
		Collections.sort(minus);
		Collections.sort(plus);
		
		for(int i = minus.size()-1; i>=0; i-=booksThatCanLift) {
			System.out.println(minus.get(i));
			result+=minus.get(i)*2;
		}
		for(int i = plus.size()-1; i>=0; i-=booksThatCanLift) {
			result+=plus.get(i)*2;
		}
		if(plus.size()>0&&minus.size()>0) {
			result-=Math.max(minus.get(minus.size()-1),plus.get(plus.size()-1));
		}else if(plus.size()>0) {
			result-=plus.get(plus.size()-1);
		}else if(minus.size()>0) {
			result-=minus.get(minus.size()-1);
		}
		System.out.println(result);
	}
}

















