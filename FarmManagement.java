import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class FarmManagement {
	public static void main(String[] args) {
		try {
			new FindMountainTopNum();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class FindMountainTopNum{
	private Mountain[][] mountain;
	private int horizon;
	private int vertical;
	private int result;
	public FindMountainTopNum() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		vertical = Integer.parseInt(st.nextToken());
		horizon = Integer.parseInt(st.nextToken());
		mountain = new Mountain[horizon][vertical];
		for(int i = 0; i<vertical; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < horizon; j++) {
				Mountain newMountain = new Mountain(Integer.parseInt(st.nextToken()));
				mountain[j][i] = newMountain;
			}
		}
		FindMountainTop();
		bw.write(result+"");
		bw.flush();
	}
	public void FindMountainTop() {
		for(int y = 0; y<vertical; y++) {
			for(int x = 0; x<horizon; x++) {
				if(!mountain[x][y].isMountainTop()) {
					continue;
				}
				boolean b = isThisMountainTop(x,y);
				if(b) {
					result++;
				}
			}
		}
	}
	public boolean isThisMountainTop(int x, int y) {
		if(mountain[x][y].isAlreadyVisited())return false;
		for(int i = x-1; i<=x+1; i++) {
			for(int j = y-1; j <= y+1; j++) {
				boolean isOutOfRange = i<0||j<0||horizon<=i||vertical<=j; 
				boolean isOneSelf = x==i&&y==j;
				if(isOutOfRange||isOneSelf) {
					continue;
				}
				mountain[x][y].setAlreadyVisited(true);
				if(mountain[x][y].getHeight()<mountain[i][j].getHeight()) {
					mountain[x][y].setMountainTop(false);
				}
				else if(mountain[x][y].getHeight()==mountain[i][j].getHeight()) {
					if(!mountain[i][j].isMountainTop()) {
						mountain[x][y].setMountainTop(false);
					}
					if(!mountain[x][y].isMountainTop()) {
						mountain[i][j].setMountainTop(false);
					}
					else if(!mountain[i][j].isAlreadyVisited()) {
						boolean b = isThisMountainTop(i, j);
						mountain[x][y].setMountainTop(b);
					}
				}
			}
			
		}
		return mountain[x][y].isMountainTop();
	}
}
class Mountain{
	private int height;
	private boolean isMountainTop;

	private boolean isAlreadyVisited;

	public Mountain(int height){
		this.height = height;
		isMountainTop = true;
		isAlreadyVisited = false;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isMountainTop() {
		return isMountainTop;
	}
	public void setMountainTop(boolean isMountainTop) {
		this.isMountainTop &= isMountainTop;
	}
	
	public boolean isAlreadyVisited() {
		return isAlreadyVisited;
	}

	public void setAlreadyVisited(boolean isAlreadyVisited) {
		this.isAlreadyVisited = isAlreadyVisited;
	}
	
}