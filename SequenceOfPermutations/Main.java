import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new SequenceOfPermutations();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class SequenceOfPermutations{
	private int arrayNum;
	private long[] factorial; 
	public SequenceOfPermutations() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arrayNum = Integer.parseInt(br.readLine());
		factorial = new long[arrayNum];
		long tmp = 1;
		for(int i = 1; i<arrayNum; i++) {
			if(i!=0) {
				tmp *= i;
				factorial[arrayNum-i-1] = tmp;
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		byte type = Byte.parseByte(st.nextToken());
		if(type==1) {
			int[] output = NthSequence(Long.parseLong(st.nextToken())-1);
			for(int i = 0; i<arrayNum; i++) {
				if(i==arrayNum-1) {
					System.out.println(output[i]);
				}
				else{
					System.out.print(output[i]+" ");
				}
			}
		}
		if(type==2) {
			int[] inputArray = new int[arrayNum];
			for(int i = 0; i<arrayNum; i++) {
				inputArray[i] = Integer.parseInt(st.nextToken());
			}
			System.out.print(FindSequenceNth(inputArray));
		}
	}
	public long FindSequenceNth(int[] array) {
		long result = 0;
		boolean[] isAlreadySelect = new boolean[arrayNum];
		Arrays.fill(isAlreadySelect, false);
		for(int i = 0; i<arrayNum; i++) {
			int idx = 0;
			for(int j = 0; j<arrayNum; j++) {
				if(j==array[i])break;
				if(!isAlreadySelect[j])idx++;
			}
			result += (idx-1)*factorial[i];
			isAlreadySelect[array[i]-1] = true;
		}
		return (result+1);
	}
	public int[] NthSequence(long n){
		boolean[] isAlreadySelect = new boolean[arrayNum];
		int[] output = new int[arrayNum];
		Arrays.fill(isAlreadySelect, false);
		
		for(int i = 0; i<arrayNum; i++) {
			
			long j;
			if(factorial[i]==0) {
				j = 0;
			}
			else {
				j = n/factorial[i];
			}
			
			int idx = 0;
			if(j==0) {
				
				while(isAlreadySelect[idx]) {
					idx++;
				}
				
			}
			else {
				
				while(j>=0) {
					if(!isAlreadySelect[idx]) {
						j--;
					}
					if(j<0)break;
					else idx++;
				}
				
			}
			isAlreadySelect[idx] = true;
			output[i] = idx+1;
			if(factorial[i]!=0) {
				n%=factorial[i];
			}
		}
		return output;
	}
	
	
}
