import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Dise {

	public static void main(String[] args) {
		try {
			new MinDise();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class MinDise{
	final static int MAX_DISE_NUM = 50;
	
	private long diseNumRootThree;
	private ArrayList<Integer> diseEachSideNum;
	private long minOneSide;
	private long minTwoSide;
	private long minThreeSide;
	public MinDise() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		diseNumRootThree = Integer.parseInt(br.readLine());
		diseEachSideNum = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<6; i++) {
			diseEachSideNum.add(Integer.parseInt(st.nextToken()));
		}
	
		if(diseNumRootThree==1) {
			Collections.sort(diseEachSideNum);
			int sum = 0;
			for (int i = 0; i < 5; i++) {
				sum += diseEachSideNum.get(i);
			}
			System.out.println(sum);
			return;
		}
		minOneSide = Collections.min(diseEachSideNum);
		minTwoSide = SearchMinSumTwoSide();		
		minThreeSide = SearchMinSumThreeSide();
		System.out.println(minOneSide+ " " + minTwoSide + " " + minThreeSide);
		long oneSideEachNumSum = (4*minThreeSide);
		long twoSideEachNumSum = (8*(diseNumRootThree-2)+4)*minTwoSide;
		long threeSideEachNumSum = (5*diseNumRootThree*diseNumRootThree-16*diseNumRootThree+12)*minOneSide;
		System.out.println(oneSideEachNumSum+" " + twoSideEachNumSum + " " + threeSideEachNumSum);
		long result = oneSideEachNumSum + twoSideEachNumSum + threeSideEachNumSum;
		System.out.println(result);
	}
	
	public int SearchMinSumTwoSide() {
		int min = MAX_DISE_NUM*2;
		for(int i = 0; i < 6; i++) {
			for(int j = i+1; j < 6; j ++) {
				if(i+j != 5) {
					min = Math.min(min, diseEachSideNum.get(i)+diseEachSideNum.get(j));
				}
			}
		}
		return min;
	}
	public int SearchMinSumThreeSide() {
		int min = 0;
		for(int i = 0; i < 3; i ++) {
			min += Math.min(diseEachSideNum.get(i), diseEachSideNum.get(5-i));
		}
		return min;
	}
}








