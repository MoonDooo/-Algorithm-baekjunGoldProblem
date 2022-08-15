import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		try {
			new CrazyRobot();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class CrazyRobot{
	private int maxMoveNum;
	private int moveNum;
	private boolean[][] isPassSite;
	private double[] byDirectionProbability;
	private double result;
	
	public final static int[] EAST_WEST = {1, -1, 0, 0};
	public final static int[] SORTH_NORTH = {0, 0, -1, 1};
	public CrazyRobot() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		maxMoveNum = Integer.parseInt(st.nextToken());
		int groundSize = maxMoveNum*2+1;
		isPassSite = new boolean[groundSize][groundSize];
		
		byDirectionProbability = new double[4];
		for(int i =0; i<4; i++) {
			byDirectionProbability[i] = Double.parseDouble(st.nextToken())/100;
		}

		moveRobotOnlySimply(groundSize/2, groundSize/2, 1);
		System.out.println(result);
		
	}
	public void moveRobotOnlySimply(int x, int y, double moveProbability) {
		isPassSite[x][y] = true;
		if(moveNum==maxMoveNum) {
			result+=moveProbability;
			moveNum--;
			isPassSite[x][y] = false;
			return;
		}
		for(int i = 0; i<4; i++	) {
			double tmp = moveProbability;
			if(!isPassSite[x+EAST_WEST[i]][y+SORTH_NORTH[i]]) {
				moveNum++;
				tmp*=byDirectionProbability[i];
				moveRobotOnlySimply(x+EAST_WEST[i], y+SORTH_NORTH[i], tmp);
			}
		}
		isPassSite[x][y] = false;
		moveNum--;
	}
	
	
}