import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @백준 1451번
 * @author MoonDooo
 */
public class Main {
	public static void main(String[] args) {
		try {
			new DivideRect();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class DivideRect{
	private int height;
	private int width;
	private	int[][] rect;
	private long result;
	public DivideRect() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		rect = new int[width][height];
		for(int i = 0; i < height; i++) {
			String input = br.readLine();
			for(int j = 0; j <width; j++) {
				rect[j][i] = input.charAt(j)-'0';
			}
		}
		result = 0;
		DivideSameWidth();
		DivideSameHeight();
		DivideColOneAndTwo();
		DivideColTwoAndOne();
		DivideRowOneAndTwo();
		DivideRowTwoAndOne();
		System.out.println(result);
	}
	
	public void DivideSameWidth() {
		long r1Sum, r2Sum, r3Sum;
		for(int i = 0; i<height-1; i++) {
			for(int j = 0; j<height; j++) {
				r1Sum = SumInRectNum(0, width-1, 0, i);
				r2Sum = SumInRectNum(0, width-1, i+1, j);
				r3Sum = SumInRectNum(0, width-1, j+1, height-1);
				result = Math.max((r1Sum*r2Sum*r3Sum), result);
			}
		}
	}
	
	public void DivideSameHeight() {
		long r1Sum, r2Sum, r3Sum;
		for(int i = 0; i<width-1; i++) {
			for(int j = 0; j<width; j++) {
				r1Sum = SumInRectNum(0, i, 0, height-1);
				r2Sum = SumInRectNum(i+1, j, 0, height-1);
				r3Sum = SumInRectNum(j+1, width-1, 0,  height-1);
				result = Math.max((r1Sum*r2Sum*r3Sum), result);
			}
		}
	}
	
	public void DivideColOneAndTwo() {
		//ㅜ
		long r1Sum, r2Sum, r3Sum;
		for(int i = 0; i<height-1; i++) {
			for(int j =0; j<width-1; j++) {
				r1Sum = SumInRectNum(0, width-1, 0, i);
				r2Sum = SumInRectNum(0, j, i+1, height-1);
				r3Sum = SumInRectNum(j+1, width-1, i+1,  height-1);
				result = Math.max((r1Sum*r2Sum*r3Sum), result);
			}
		}
	}
	
	public void DivideColTwoAndOne() {
		//ㅗ
		long r1Sum, r2Sum, r3Sum;
		for(int i = 0; i<height-1; i++) {
			for(int j =0; j<width-1; j++) {
				r1Sum = SumInRectNum(0, j, 0, i);
				r2Sum = SumInRectNum(j+1, width-1, 0, i);
				r3Sum = SumInRectNum(0, width-1, i+1,  height-1);
				result = Math.max((r1Sum*r2Sum*r3Sum), result);
			}
		}
	}
	
	public void DivideRowOneAndTwo() {
		//ㅏ
		long r1Sum, r2Sum, r3Sum;
		for(int i = 0; i<width-1; i++) {
			for(int j = 0; j<height-1; j++) {
				r1Sum = SumInRectNum(0, i, 0, height-1);
				r2Sum = SumInRectNum(i+1, width-1, 0, j);
				r3Sum = SumInRectNum(i+1, width-1, j+1,  height-1);
				result = Math.max((r1Sum*r2Sum*r3Sum), result);
			}
		}
	}
	
	public void DivideRowTwoAndOne() {
		//ㅓ
		long r1Sum, r2Sum, r3Sum;
		for(int i = 0; i<width-1; i++) {
			for(int j = 0; j<height-1; j++) {
				r1Sum = SumInRectNum(0, i, 0, j);
				r2Sum = SumInRectNum(0, i, j+1, height-1);
				r3Sum = SumInRectNum(i+1, width-1, 0,  height-1);
				result = Math.max((r1Sum*r2Sum*r3Sum), result);
			}
		}
	}
	
	public int SumInRectNum(int startWidth, int endWidth, int startHeight, int endHeight) {
		int sumNum = 0;
		for(int i = startHeight; i <= endHeight; i++) {
			for(int j = startWidth; j <= endWidth; j++) {
				sumNum+=rect[j][i];
			}
		}
		return sumNum;
	}
}












