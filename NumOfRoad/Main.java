import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new NumOfRoad();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class NumOfRoad{
	private int width;
	private int height;
	private ArrayList<BrokenRoad> brokenRoads;
	private long[][] roads;
	private boolean[][] isVisited;
	public NumOfRoad() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		
		brokenRoads = new ArrayList<>();
		int brokenRoadNum = Integer.parseInt(br.readLine());
		for(int i = 0; i<brokenRoadNum; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			BrokenRoad newBrokenRoad = new BrokenRoad(a, b, c, d);
			brokenRoads.add(newBrokenRoad);
		}
		
		roads = new long[width+1][height+1];
		isVisited = new boolean[width+1][height+1];
		for(long[] a : roads) {
			Arrays.fill(a, 0);
		}
		for(boolean[] b : isVisited) {
			Arrays.fill(b, false);
		}
		
		roads[0][0]=1;
		System.out.println(FindCaseNumOfRoad(width, height));

	}
	
	public long FindCaseNumOfRoad(int x, int y) {
		if(x<0||y<0)return 0;
		if(x==0&&y==0) {
			return 1;
		}
		if(isVisited[x][y]) {
			return roads[x][y];
		}
		isVisited[x][y]=true;
		
		boolean isBrokenXVector = false;
		boolean isBrokenYVector = false;
		for(int i = 0; i<brokenRoads.size(); i++) {
			isBrokenXVector |= brokenRoads.get(i).IsBroken(x-1, y, x, y);
			isBrokenYVector |= brokenRoads.get(i).IsBroken(x, y-1, x, y);
		}
		long a=0, b=0;
		
		if(!isBrokenXVector) {
			a = FindCaseNumOfRoad(x-1, y);
		}
		if(!isBrokenYVector) {
			b = FindCaseNumOfRoad(x, y-1);
		}
		roads[x][y] = a+b;
		return (a+b);
	}
}
class BrokenRoad{
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	
	public BrokenRoad(int a, int b, int c, int d) {
		if(a<c) {
			startX = a;
			endX = c;
		}
		else if(a>c){
			startX = c;
			endX = a;
		}
		else {
			startX = c;
			endX = a;
		}
		
		if(b<d) {
			startY = b;
			endY = d;
		}
		else if(b>d) {
			startY = d;
			endY = b;
		}
		else {
			startY = d;
			endY = b;
		}
	}
	
	public boolean IsBroken(int a, int b, int c, int d) {
		return (startX==a&&startY==b&&endX==c&&endY==d);
	}

}
