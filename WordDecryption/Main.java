import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/**
 * 백준 1593번
 * @author MoonDooo
 */
public class Main {
	public static void main(String[] args) {
		try {
			new WordDecryption();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class WordDecryption{
	private int wordLength;
	private int stringLength;
	private int result;
	private HashMap<Character, Integer> inputWordAlpMap;
	public WordDecryption() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		wordLength = Integer.parseInt(st.nextToken());
		stringLength = Integer.parseInt(st.nextToken());
		result = 0;
		
		String inputWord = br.readLine();
		String inputString = br.readLine();
		
		inputWordAlpMap = new HashMap<Character, Integer>();
		for(int i = 0; i<wordLength; i++) {
			inputWordAlpMap.put(inputWord.charAt(i), inputWordAlpMap.getOrDefault(inputWord.charAt(i), 0)+1);
		}
		
		HashMap<Character, Integer> inputSubStringMap = new HashMap<Character, Integer>();
		int start = 0;
		int size =0;
		for(int i = 0; i < stringLength; i++) {
			char inputChar = inputString.charAt(i);
			inputSubStringMap.computeIfPresent(inputChar, (K, V)->V+1);
			inputSubStringMap.computeIfAbsent(inputChar, V->1);
			size++;
			if(inputSubStringMap.equals(inputWordAlpMap)) {
				result++;
			}
			
			if(size==wordLength) {
				if(inputSubStringMap.get(inputString.charAt(start))==1) {
					inputSubStringMap.remove(inputString.charAt(start));
				}
				else {
					inputSubStringMap.computeIfPresent(inputString.charAt(start), (K, V)->V-1);
				}
				start++;
				size--;
			}
		}
		
		System.out.println(result);
		
	}
}














