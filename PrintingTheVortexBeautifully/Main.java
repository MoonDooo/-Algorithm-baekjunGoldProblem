import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		try {
			new PrintingTheVortexBeautifully();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class PrintingTheVortexBeautifully{
	Point point;
	int row1, col1, row2, col2;
	int width, height;
	private static final int[][] VORTEX = {{0, 1},{-1, 0},{0, -1},{1, 0}};
	private int[][] resultArray;
	public PrintingTheVortexBeautifully() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row1 = Integer.parseInt(st.nextToken());
		col1 = Integer.parseInt(st.nextToken());
		row2 = Integer.parseInt(st.nextToken());
		col2 = Integer.parseInt(st.nextToken());
		point = new Point();
		width = col2-col1+1;
		height = row2-row1+1;
		resultArray = new int[height][width];
		

		int num = 1;
		int vortexLoop = 1;
		int maxDigit = 0;
		boolean isSecond = false;
		while(num<=100020001) {
			for(int i = 0; i<4; i++) {
				
				for(int j = 0; j<vortexLoop; j++) {
					if(row1<=point.x&&point.x<=row2&&col1<=point.y&&point.y<=col2) {
						resultArray[point.x-row1][point.y-col1]=num;
						maxDigit = Math.max(maxDigit, (int)Math.log10(num)+1);
					}
					point.x += VORTEX[i][0];
					point.y += VORTEX[i][1];
					num++;
				}
				
				if(isSecond) {
					vortexLoop++;
					isSecond = false;
				}else {
					isSecond = true;
				}
			}
		}
		StringBuilder result = new StringBuilder();
		for(int i = 0; i<height; i++) {
			for(int j = 0; j<width; j++) {
				int thisDigit;
				if((thisDigit = (int)Math.log10(resultArray[i][j])+1)<maxDigit) {
					for(int k = thisDigit; k<maxDigit; k++	) {
						result.append(" ");
					}
				}
				result.append(resultArray[i][j]+" ");
			}
			result.append("\n");
		}
		
		System.out.println(result);
	}
}















