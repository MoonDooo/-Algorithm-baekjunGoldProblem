import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class HeadTapTap {
	public static void main(String[] args) {
		try{
			new HeadTapTapCount();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class HeadTapTapCount{
	int arraySize;
	int[] NumDistribution;
	ArrayList<Integer> paper;
	public HeadTapTapCount() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		NumDistribution = new int[1000000];
		Arrays.fill(NumDistribution, 0);
		paper = new ArrayList<>();
		arraySize = Integer.parseInt(br.readLine());
		
		for(int i = 0; i<arraySize; i++	) {
			paper.add(Integer.parseInt(br.readLine()));
			NumDistribution[paper.get(i)]++;
	}
		for(int i = 0; i<arraySize; i++) {
			int result = 0;
			ArrayList<Integer> divisor = new ArrayList<>();
			for(int j = 1; j <= Math.sqrt(paper.get(i)); j++) {
				if(paper.get(i)%j==0) {
					divisor.add(j);
					if(j!=paper.get(i)/j) {
						divisor.add(paper.get(i)/j);
					}
				}
			}
			for(int j = 0; j < divisor.size(); j++) {
				result += NumDistribution[divisor.get(j)]; 
			}
			bw.write((result-1)+"\n");
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
