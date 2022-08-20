import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		try {
			new Flip();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class Flip{
	public Flip() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringBuilder output = new StringBuilder("");
		for(int i = 0; i<input.length(); i++){
			char c = input.charAt(i);
			if(output.toString().isEmpty()||c<=output.charAt(0)) {
				output.insert(0, c);
			}
			else {
				output.append(c);
			}
		}
		System.out.println(output.toString());
	}
}
