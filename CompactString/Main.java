import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준 1662번
 * @author MoonDooo
 */
public class Main {

	public static void main(String[] args) {
		try {
			new CompactString();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class CompactString {
	private Stack<Character> stack;
	private int result;
	public CompactString() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		stack = new Stack<>();
		for(char c : input.toCharArray()) {
			stack.add(c);
		}
		
		
		result  = 0;
		while(stack.size()!=0) {
			char c = stack.pop();
			if('0'<=c&&c<='9') {
				result++;
			}else if(c==')') {
				result += ExtractString();
			}
		}
		
		System.out.println(result);
	}
	public int ExtractString() {
		int size=0;
		char c;
		while((c = stack.pop())!='(') {
			if('0'<=c&&c<='9') {
				size++;
			}
			else if(c==')') {
				size += ExtractString();
			}
		}
		int num = (int)(stack.pop()-'0');
		return num*size;
	}
}
