import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class RemoteControl {

	public static void main(String[] args) {
		try{
			new MinClickForChannel();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
class MinClickForChannel {
	private int goalChannel;
	private int brokenButtonNum;
	private boolean[] brokenButton;
	private int minDifferenceToChannel;
	private int buttonClickCounter;
	public MinClickForChannel() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		goalChannel = Integer.parseInt(br.readLine());
		brokenButtonNum = Integer.parseInt(br.readLine());
		brokenButton = new boolean[10];
		
		StringTokenizer st = null;
		if(brokenButtonNum!=0) {
			st = new StringTokenizer(br.readLine());
		}

		for(int i = 0; i<brokenButtonNum; i++) {
			brokenButton[ Integer.parseInt(st.nextToken())] = true;
		}
		
		int result = Math.abs(goalChannel-100);
		minDifferenceToChannel = Integer.MAX_VALUE;
		for(int i = 0; i<1000000;i++) {
			int num = i;
			if(num==0)buttonClickCounter=1;
			else buttonClickCounter= (int)(Math.log10(num))+1;
			boolean isBroken = false;
			for(int j = 0; j<buttonClickCounter; j++) {
				int idx = num%10;
				num /= 10;
				if(brokenButton[idx]) {
					isBroken = true;
					break;
				}
			}
			if(!isBroken) {	
				minDifferenceToChannel = Math.min(Math.abs(goalChannel-i)+buttonClickCounter, minDifferenceToChannel);
				result = Math.min(minDifferenceToChannel, result);
			}
		}
		
		bw.write(result+"");
		bw.flush();
		bw.close();
		br.close();
	}
	
}












	