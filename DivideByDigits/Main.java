import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		try {
			new DivideByDigit();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class DivideByDigit{
	private Queue<Long> queue;
	private long input;
	private boolean[] isInputDigit;
	private ArrayList<Byte> digit;
	public DivideByDigit() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = Long.parseLong(br.readLine());
		

		isInputDigit = new boolean[10];
		digit = new ArrayList<>();
		InputDivideDigit();
		
		queue = new LinkedList<>();
		queue.add(input);
		while(true) {
			long q = queue.poll();
			boolean isDivideAllDigit = true;
			for(int i = 0; i<digit.size(); i++) {
				isDivideAllDigit &= q%digit.get(i)==0;
			}
			if(isDivideAllDigit) {
				System.out.println(q);
				return;
			}
			for(int i = 0; i<10; i++) {
				queue.add(q*10+i);
			}
		}
	}
	
	public void InputDivideDigit() {
		long tmp = input;
		while(tmp != 0){
			if(tmp%10!=0) {
				isInputDigit[(int) (tmp%10)]= true;
			}
			tmp = tmp/10;
		}
		for(int i = 0; i<10; i++) {
			if(isInputDigit[i]) {
				digit.add((byte)i);
			}
		}
	}
}
