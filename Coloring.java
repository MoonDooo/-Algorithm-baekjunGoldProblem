import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/**
 * @백준 1117번
 * @author MoonDooo
 */
public class Coloring {

	public static void main(String[] args) {
		try{
			new UnColoringAreaNum();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class UnColoringAreaNum{
	private long width;
	private long height;
	private long xCoordinateToFold;
	private long yCoordinatesNumToDivide;
	private long x1ToColoring;
	private long y1ToColoring;
	private long x2ToColoring;
	private long y2ToColoring;	
	
	private long result;
	public UnColoringAreaNum() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		InitializeInputValues(br.readLine());
		
		long widthColoring = 0;
		if(2*xCoordinateToFold<=width) {
			if(x1ToColoring>=xCoordinateToFold)widthColoring = (x2ToColoring-x1ToColoring);
			else widthColoring = (x2ToColoring-x1ToColoring)+Math.min(xCoordinateToFold, x2ToColoring)-x1ToColoring;
		}
		else if(2*xCoordinateToFold>width) { 
			if(width<=x1ToColoring+xCoordinateToFold)widthColoring = (x2ToColoring-x1ToColoring);
			else widthColoring = (x2ToColoring-x1ToColoring)+Math.min(width, xCoordinateToFold+x2ToColoring)-(xCoordinateToFold+x1ToColoring);
		}
		widthColoring *= y2ToColoring-y1ToColoring;	
		result = (width*height)-(widthColoring*(yCoordinatesNumToDivide+1));
		bw.write(result+"");
		bw.flush();
		br.close();
		bw.close();
	}
	public void InitializeInputValues(String s) {
		StringTokenizer st = new StringTokenizer(s, " ");
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		xCoordinateToFold = Integer.parseInt(st.nextToken());
		yCoordinatesNumToDivide = Integer.parseInt(st.nextToken());
		x1ToColoring = Integer.parseInt(st.nextToken());
		y1ToColoring = Integer.parseInt(st.nextToken());
		x2ToColoring = Integer.parseInt(st.nextToken());
		y2ToColoring = Integer.parseInt(st.nextToken());	
	}
}










