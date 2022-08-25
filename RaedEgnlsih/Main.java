import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new RaedEgnlsih();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class RaedEgnlsih{
	private int dictionaryNum;
	private int outputNum;
	private Word[] dictionary;
	public RaedEgnlsih() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dictionaryNum = Integer.parseInt(br.readLine());
		dictionary = new Word[dictionaryNum];
		for(int i = 0; i<dictionaryNum; i++) {
			Word newWord = new Word(br.readLine());
			dictionary[i] = newWord;
		}	
		
		outputNum = Integer.parseInt(br.readLine());
		for(int i =0; i<outputNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int result = 0;
			while(st.hasMoreTokens()) {
				int count = 0;
				Word inputWord = new Word(st.nextToken());
				for(int j =0; j<dictionaryNum; j++) {
					if(inputWord.getFirstAndEnd().equals(dictionary[j].getFirstAndEnd())
							&&Arrays.deepEquals(inputWord.getAlphabetNum(), dictionary[j].getAlphabetNum())) {
						count++;
					}
				}
				if(result==0) {
					result+=count;
				}
				else if(count!=0){
					result*=count;
				}
			}
			System.out.println(result);
		}
	}
}
class Word{
	private Integer[] alphabetNum;
	private String firstAndEnd;
	
	public Word(String s){
		alphabetNum = new Integer[52];
		Arrays.fill(alphabetNum, 0);
		for(int i = 1; i<s.length()-1; i++){
			char c = s.charAt(i);
			if('A'<=c&&c<='Z') {
				alphabetNum[c-'A']++;
			}
			else {
				alphabetNum[c-'a'+26]++;
			}
		}
		if(s.length()-1!=0) {
			firstAndEnd = s.charAt(0)+""+s.charAt(s.length()-1);	
		}
		else {
			firstAndEnd = s.charAt(0)+"";
		}
	}
	
	public Integer[] getAlphabetNum() {
		return alphabetNum;
	}

	public String getFirstAndEnd() {
		return firstAndEnd;
	}


}
