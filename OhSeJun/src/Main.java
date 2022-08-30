import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * 백준 1570번
 * @author MoonDooo
 */
public class Main {
	public static void main(String[] args) {
		try {
			new OhSeJun();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class OhSeJun{
	private int commandLength;
	private int ohSeJunX;
	private int ohSeJunY;
	private int mineX;
	private int mineY;
	private StringBuilder result;
	private ArrayList<StringBuilder> resultArray;
	public OhSeJun() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		commandLength = Integer.parseInt(st.nextToken());
		ohSeJunX = Integer.parseInt(st.nextToken());
		ohSeJunY = Integer.parseInt(st.nextToken());
		mineX = Integer.parseInt(st.nextToken());
		mineY = Integer.parseInt(st.nextToken());
		resultArray = new ArrayList<>();
		
		if(mineX<ohSeJunX||mineY<ohSeJunY) {
			System.out.println(-1);
			return;
		}
		int seJunXToMineXDistance = mineX-ohSeJunX;
		int seJunYToMineYDistance = mineY-ohSeJunY;
		
		int cycleNum = (seJunXToMineXDistance+seJunYToMineYDistance)/commandLength;
		
		int cycleRNum = commandLength;
		int cycleUNum = 0;
		while(0<=cycleRNum) {
			int distanceXWhenLastCycle = seJunXToMineXDistance-cycleRNum*cycleNum;
			int distanceYWhenLastCycle = seJunYToMineYDistance-cycleUNum*cycleNum;
			
			if(distanceXWhenLastCycle>cycleRNum||distanceYWhenLastCycle>cycleUNum) {
				cycleRNum--;
				cycleUNum++;
				continue;
			}
			if(distanceXWhenLastCycle<0||distanceYWhenLastCycle<0) {
				cycleRNum--;
				cycleUNum++;
				continue;
			}
			result = new StringBuilder();
			int remainCommandR = cycleRNum-distanceXWhenLastCycle;
			int remainCommandU = cycleUNum-distanceYWhenLastCycle;
			while(result.length()<commandLength) {
				if(0<distanceXWhenLastCycle) {
					distanceXWhenLastCycle--;
					result.append('R');
				}
				else if(0<distanceYWhenLastCycle) {
					distanceYWhenLastCycle--;
					result.append('U');
				}
				else if(0<remainCommandR) {
					remainCommandR--;
					result.append('R');
				}
				else if(0<remainCommandU) {
					remainCommandU--;
					result.append('U');
				}
				else {
					result.append('R');
				}
			}	
			resultArray.add(result);
			cycleRNum--;
			cycleUNum++;
		}
		Collections.sort(resultArray);
		if(0<resultArray.size()) {
			System.out.println(resultArray.get(0));
		}
		else {
			System.out.println(-1);
		}
	}
}