import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		try {
			new Game();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class Game{
	public final static byte DEATH_AREA = -1;
	public final static byte DANGEROUS_AREA = 1;
	public final static byte SAFE_AREA = 0;
	public final static int[] MOVE_TO_X = {-1, 1, 0, 0};
	public final static int[] MOVE_TO_Y = {0, 0, -1, 1};
	private byte[][] length;
	private int[][] node;
	private boolean[][] isVisitied;
	Deque<Point> deque;
	public Game() throws IOException{
		
		
		isVisitied = new boolean[501][501];
		length = new byte[501][501];
		node = new int[501][501];
		for(int[] a : node) {
			Arrays.fill(a, Integer.MAX_VALUE);
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int DangerousAreaNum = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i<DangerousAreaNum; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			ArrayInit(x1,y1,x2,y2, DANGEROUS_AREA);
		}
		
		int DeathAreaNum = Integer.parseInt(br.readLine());
		for(int i = 0; i<DeathAreaNum; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			ArrayInit(x1,y1,x2,y2, DEATH_AREA);
		}
		length[0][0]=SAFE_AREA;
		node[0][0] = 0;
		Point nowPoint = new Point(0,0);
		
		deque = new LinkedList<>();
		deque.add(nowPoint);
		nowPoint.setPoint(0,0);	
		while(!deque.isEmpty()) {
			Point now = deque.removeFirst();
			for(int i = 0; i<4; i++) {
				int nextPointX = now.x+MOVE_TO_X[i];
				int nextPointY = now.y+MOVE_TO_Y[i];
				boolean isOutOfRange = (nextPointX<0)||(nextPointY<0)||(500<nextPointX)||(500<nextPointY);
				boolean isDeathArea = false;
				if(!isOutOfRange) {
					isDeathArea = length[nextPointX][nextPointY]==-1;
				}
				
				if(!isOutOfRange&&!isDeathArea) {
					 int DistanceToNext = node[now.x][now.y] + length[nextPointX][nextPointY];
					 node[nextPointX][nextPointY] = Math.min(node[nextPointX][nextPointY], DistanceToNext);
					 if(!isVisitied[nextPointX][nextPointY]) {
						 if(length[nextPointX][nextPointY]==0) {
							 deque.addFirst(new Point(nextPointX,nextPointY));
							 isVisitied[nextPointX][nextPointY] = true;
						 }
						 else if(length[nextPointX][nextPointY]==1) {
							 deque.addLast(new Point(nextPointX,nextPointY));
							 isVisitied[nextPointX][nextPointY] = true;
						 }
					 }
				}
				
			}
			
		
		}
		if(node[500][500] == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(node[500][500]);
		}
	}
	
	public void ArrayInit(int x1, int y1, int x2, int y2, byte init) {
		if(x2<x1) {
			int tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		if(y2<y1) {
			int tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		
		for(int x = x1; x<=x2; x++) {
			for(int y = y1; y<= y2; y++) {
				length[x][y] = init;
			}
			
		}
	}
}

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void setPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
}








