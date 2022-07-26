import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @백준 1106번
 * @author MoonDooo
 */
public class Hotel {
	public static void main(String[] args) {
		try {
			new MinCostForAd();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class MinCostForAd{
	private int cityNum;
	private int targetCustomerNum;
	private int[] targetCustomer;
	private int result;
	public MinCostForAd() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		targetCustomerNum = Integer.parseInt(st.nextToken());
		targetCustomer = new int[targetCustomerNum+101];
		Arrays.fill(targetCustomer, Integer.MAX_VALUE);
		cityNum = Integer.parseInt(st.nextToken());
		targetCustomer[0] = 0;
		for(int i = 0; i<cityNum; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int people = Integer.parseInt(st.nextToken());
			for(int j = people; j < targetCustomerNum+101; j++	) {
				if(targetCustomer[j-people]!=Integer.MAX_VALUE) {
					targetCustomer[j] = Math.min(targetCustomer[j], targetCustomer[j-people]+cost);
				}
			}
		}
		result = Integer.MAX_VALUE;
		for(int i = targetCustomerNum; i<targetCustomerNum+101; i++) {
			result = Math.min(targetCustomer[i], result);
		}
		bw.write(result+"");
		bw.flush();
		bw.close();
		br.close();
	}
}