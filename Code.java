import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Code {
	public static void main(String[] args) {
		try {
			new FindPasswordAttempts();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class FindPasswordAttempts{
	private String codeChars;
	private String truthCode;
	private long result;
	public FindPasswordAttempts() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		codeChars = br.readLine();
		truthCode = br.readLine();
		
		result = 0;
		FindPassword();
		result = CompressionNum(result);
		
		System.out.println(result+"");
	}
	
	public void FindPassword() {
		long pow = 1;
		for(int i = truthCode.length()-1; i >=0; i--) {
			result += (pow*(codeChars.indexOf(truthCode.charAt(i))+1));
			result = CompressionNum(result);
			pow = pow * codeChars.length();
			pow = CompressionNum(pow);
		}
		
	}
	
	public long CompressionNum(long num) {
		return num%=900528;
	}
}
 