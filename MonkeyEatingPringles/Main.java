import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new MonkeyEatingPringles();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class Pringles{
	int start;
	int end;
	public Pringles(int s, int e) {
		start = s;
		end = e;
	}
}
class MonkeyEatingPringles{
	ArrayList<Pringles> pringlesArray;
	int[][] result;
	int pringlesNum;
	public MonkeyEatingPringles() throws IOException{
		pringlesArray = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		pringlesNum = Integer.parseInt(br.readLine());
		
	
		StringTokenizer st;
		for(int i = 0; i<pringlesNum; i++) {
			st = new StringTokenizer(br.readLine());
			pringlesArray.add(new Pringles(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		result = new int[pringlesArray.get(0).end-pringlesArray.get(0).start+1][pringlesNum];
		
		int min = Integer.MAX_VALUE;
		int minIdx = 0;
		for(int i = pringlesArray.get(0).start; i<=pringlesArray.get(0).end; i++) {
			int stress = i;
			int preStress = stress;
			int tmp = 0;
			result[i-pringlesArray.get(0).start][0] = stress;
			for(int j = 1; j < pringlesNum; j++) {
				stress = FindStartStress(stress, j);
				tmp += Math.abs(stress-preStress);
				preStress = stress;
				result[i-pringlesArray.get(0).start][j] = stress;
			}
			if(min>tmp) {
				minIdx = i-pringlesArray.get(0).start;
				min = tmp;
			}
		}
		
		bw.write(min+"\n");
		for(int i =0; i<pringlesNum; i++) {
			bw.write(result[minIdx][i]+"\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	public int FindStartStress(int stress, int idx) {
		if(stress<pringlesArray.get(idx).start) {
			return pringlesArray.get(idx).start;
		}else if(pringlesArray.get(idx).end<stress){
			return pringlesArray.get(idx).end;
		}else {
			return stress;
		}
	}
}









