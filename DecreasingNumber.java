import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.IOException;
public class DecreasingNumber {

	public static void main(String[] args) {
		try {
			new ExploringDecreasingNumber();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
class ExploringDecreasingNumber{
	private ArrayList<Long> list = new ArrayList<>();
	public ExploringDecreasingNumber() throws IOException {
		int brNumber = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		
		if(brNumber == 0) System.out.println("0");
		else if(brNumber>1022) System.out.println("-1");
		else {
			for(long i = 0; i<10; i++) {
				SearchDecreasingNumber(i, 1);
			}
			Collections.sort(list);
			System.out.println(list.get(brNumber));
		}
	}
	public void SearchDecreasingNumber(long number, int digit) {
		if(digit>10)return;
		list.add(number);
		for(int i = 0; i<10; i++) {
			if(number%10>i) {
				SearchDecreasingNumber((number*10)+i, digit+1);
			}
		}
	}
}







