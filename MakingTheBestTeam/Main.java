import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new MakingTheBestTeam();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class MakingTheBestTeam{
	ArrayList<Integer> blackTeamArray;
	ArrayList<Integer> whiteTeamArray;
	int[][][] dp;
	int playerNum;
	public MakingTheBestTeam() throws IOException{
		blackTeamArray = new ArrayList<>();
		whiteTeamArray = new ArrayList<>();
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		playerNum = 0;
		for(String input = br.readLine(); input != null; input = br.readLine()) {
			st = new StringTokenizer(input);
			blackTeamArray.add(Integer.parseInt(st.nextToken()));
			whiteTeamArray.add(Integer.parseInt(st.nextToken()));
			playerNum++;
		}
		dp = new int[playerNum][16][16];
		System.out.println(FindBestTeamStrength(0, 0, 0));
	}
	public int FindBestTeamStrength(int playerIdx, int whiteNum, int blackNum) {
		if(whiteNum==15&&blackNum==15)return 0;
		if(playerIdx==playerNum)return 0;
		if(dp[playerIdx][whiteNum][blackNum]!=0)return dp[playerIdx][whiteNum][blackNum];
		
		int cin = FindBestTeamStrength(playerIdx+1, whiteNum, blackNum);
		if(whiteNum<15)cin = Math.max(cin, FindBestTeamStrength(playerIdx+1, whiteNum+1, blackNum)+whiteTeamArray.get(playerIdx));
		if(blackNum<15)cin = Math.max(cin, FindBestTeamStrength(playerIdx+1, whiteNum, blackNum+1)+blackTeamArray.get(playerIdx));
		dp[playerIdx][whiteNum][blackNum] = cin;
		return cin;
	}
}
