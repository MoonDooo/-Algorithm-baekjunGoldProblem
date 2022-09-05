import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new HighRiseBuilding();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class HighRiseBuilding{
	private int buildingNum;
	private ArrayList<Integer> buildingArray;
	private int result;
	public HighRiseBuilding() throws IOException{
		buildingArray = new ArrayList<>();
		Input();
		
		for(int i = 0; i<buildingNum; i++) {
			result = Math.max(result, CountBuildingVisible(i));
		}
		
		System.out.println(result);
	}
	
	private void Input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		buildingNum = Integer.parseInt(br.readLine());
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<buildingNum; i++) {
			buildingArray.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	public int CountBuildingVisible(int a) {
		int countBuildingVisible = 0;
		for(int b = a+1; b<buildingNum; b++) {
			if(IsCanSee(a,b)) {
				countBuildingVisible++;
			}
		}
		for(int b = a-1; b>=0; b--) {
			if(IsCanSee(b,a)) {
				countBuildingVisible++;
			}
		}
		return countBuildingVisible;
	}
	
	public boolean IsCanSee(int a, int b) {
		double height = Math.abs(buildingArray.get(b)-buildingArray.get(a));
		int width = Math.abs(b-a);
		double vector = height/width;
		if(buildingArray.get(a)<buildingArray.get(b)) {
			for(int i = a+1; i<b; i++) {
				boolean isBlind = (vector*(i-a)+buildingArray.get(a)) <= buildingArray.get(i);
				if(isBlind) {
					return false;
				}
			}
		}
		else {
			for(int i = a+1; i<b; i++) {
				boolean isBlind = (vector*(b-i)+buildingArray.get(b)) <= buildingArray.get(i);
				if(isBlind) {
					return false;
				}
			}
		}
		return true;
	}
}
