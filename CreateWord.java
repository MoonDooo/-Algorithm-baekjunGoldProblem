import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * @백준 1148번
 * @author MoonDooo
 */
public class CreateWord {

	public static void main(String[] args) {
		try {
			new makePuzzle();	
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class makePuzzle{
	private char[][] dictionary;
	private byte[] alphabetNum;
	private byte[] tmpAlphabetNum;
	public makePuzzle() throws IOException {
		dictionary = new char[200000][9];
		alphabetNum = new byte[26];
		tmpAlphabetNum = new byte[26];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int dictionaryInputIndex =0;
		String s = "";
		while(!(s = br.readLine()).equals("-")) {
			dictionary[dictionaryInputIndex] = s.toCharArray();
			dictionaryInputIndex++;
		}
		while(!(s = br.readLine()).equals("#")) {
			Arrays.fill(alphabetNum, (byte)0);
			for(char puzzleChar : s.toCharArray()) {
				alphabetNum[puzzleChar-'A']++;		
			}
			int[] usedAlphabet = new int[26];
			for(int i = 0; i < dictionaryInputIndex; i++) {
				boolean isCanMakeWord = isCanMakeWord(i);
				if(isCanMakeWord) {
					for(int j = 0; j<tmpAlphabetNum.length; j++) {
						if(tmpAlphabetNum[j]!=alphabetNum[j]) {
							usedAlphabet[j]++;
						}
					}
				}
			}
			int minResult = Integer.MAX_VALUE;
			int maxResult = Integer.MIN_VALUE;
			StringBuilder minUsedAlphabet = new StringBuilder();
			StringBuilder maxUsedAlphabet = new StringBuilder();
			for(int i = 0; i<usedAlphabet.length; i++) {
				if(alphabetNum[i]==0) {
					continue;
				}
				if(usedAlphabet[i]>maxResult) {
					maxResult = usedAlphabet[i];
					maxUsedAlphabet.setLength(0);
					maxUsedAlphabet.append((char)('A'+i));
				}
				else if(usedAlphabet[i]==maxResult) {
					maxUsedAlphabet.append((char)('A'+ i));
				}
				if(usedAlphabet[i]<minResult) {
					minResult = usedAlphabet[i];
					minUsedAlphabet.setLength(0);
					minUsedAlphabet.append((char)('A'+i));
				}
				else if(usedAlphabet[i]==minResult) {
					minUsedAlphabet.append((char)('A'+ i));
				}
			}
			StringBuilder result = new StringBuilder();
			result.append(minUsedAlphabet + " ").append(minResult + " ").append(maxUsedAlphabet + " ").append(maxResult);
			System.out.println(result);
		}
		
	}
	public boolean isCanMakeWord(int i) {
		tmpAlphabetNum = alphabetNum.clone();
		for(char dictionaryChar : dictionary[i]) {
			if(tmpAlphabetNum[dictionaryChar-'A']<=0) {
				return false;
			}
			else {
				tmpAlphabetNum[dictionaryChar-'A']--;
			}
		}
		return true;
	}
}







