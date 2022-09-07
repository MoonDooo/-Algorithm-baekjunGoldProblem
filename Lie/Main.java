import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new Lie();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class Lie{
	private int peopleNum;
	private int partyNum;
	private int[] peopleWhoKnowTruth;
	private int[] parent;
	private ArrayList<Integer>[] participantList;
	public Lie() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		peopleNum = Integer.parseInt(st.nextToken());
		partyNum = Integer.parseInt(st.nextToken());
		
		parent = new int[peopleNum];
		for(int i =0; i<peopleNum; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int peopleWhoKnowTruthNum = Integer.parseInt(st.nextToken());
		peopleWhoKnowTruth = new int[peopleWhoKnowTruthNum];
		for(int i = 0; i<peopleWhoKnowTruthNum; i++) {
			peopleWhoKnowTruth[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		
		participantList = new ArrayList[partyNum];
		for(int i = 0; i<partyNum; i++	) {
			participantList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i<partyNum; i++) {
			st = new StringTokenizer(br.readLine());
			int participantNum = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken())-1;
			participantList[i].add(a);
			while(st.hasMoreTokens()) {
				int b = Integer.parseInt(st.nextToken())-1;
				participantList[i].add(b);
				Union(a,b);	
				a = b;
			}
		}
		System.out.println(Arrays.toString(parent));
		
		int result = partyNum;
		for(int i = 0; i<partyNum; i++) {
			boolean isCanLie = true;
			for(int j = 0; j<participantList[i].size(); j++	) {
				int a = FindRootNode(participantList[i].get(j));
				for(int k = 0; k<peopleWhoKnowTruthNum; k++	) {
					int b = FindRootNode(peopleWhoKnowTruth[k]);
					System.out.println(participantList[i].get(j) + " , " + peopleWhoKnowTruth[k]);
					System.out.println(" -> " + a + " , " + b);
					if(a==b) {
						isCanLie = false;
						break;
					}
				}
				if(!isCanLie) {
					break;
				}
			}
			if(!isCanLie) {
				result --;
			}
		}
		System.out.println(result);
}
	public void Union(int a, int b) {
		int aRootNode = FindRootNode(a);
		int bRootNode = FindRootNode(b);
		if(aRootNode==bRootNode) {
			return;
		}
		else {
			parent[aRootNode]=bRootNode;
		}
	}
	
	public int FindRootNode(int a) {
		if(parent[a] == a) {
			return a;
		}
		return FindRootNode(parent[a]);
	}
}










