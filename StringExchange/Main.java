import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.xml.sax.InputSource;

public class Main {

	public static void main(String[] args) {
		try {
			new StringExchange();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class StringExchange{
	private ArrayList<Boolean> input;
	private ArrayList<Boolean> countinuousString; 
	private int aNum;
	private int bNum;
	private int result;
	public StringExchange() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputString = br.readLine();
		
		input = new ArrayList<>();
		for(int i = 0; i<inputString.length(); i++)	{
			char c = inputString.charAt(i);
			if(c=='a') {
				input.add(false);
			}
			else {
				input.add(true);
				bNum++;
			}
		}
		
		countinuousString = new ArrayList<>();
		for(int i =0; i < inputString.length(); i++) {
			if(i<bNum)countinuousString.add(true);
			else countinuousString.add(false);
		}
		
		result = Integer.MAX_VALUE;
		int length = inputString.length();
		for(int i =0; i<length; i++) {
			int resultWhenI = 0;
			for(int j=0; j<inputString.length(); j++) {
				if(countinuousString.get(j)!=input.get(j)) {
					resultWhenI++;
				}
			}
			result = Math.min(result, resultWhenI/2);
			countinuousString.add(0, countinuousString.remove(length-1));
		}
		
		System.out.println(result);
	
	}
}














