import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1414번 백준 불우이웃돕기
 * 문도오
 */
public class Main {
    public static void main(String[] args) {
       new HelpingNeighborsInNeed();
    }
}
class HelpingNeighborsInNeed{
    private final Buf buf = new Buf();
    private final int[][] input;
    private final int N;
    private final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getWeight));
    private final boolean[] isVisited;
    public HelpingNeighborsInNeed(){
        N =  buf.nextInt();
        isVisited = new boolean[N];
        input = new int[N][N];

        int allWeight = 0;
        for (int i = 0; i<N; i++){
            String inputString = buf.next();
            for (int j = 0; j<N; j++){
                int tmp = inputString.charAt(j);
                if (tmp == '0'){
                    input[i][j] = 0;
                }else if('a'<=tmp&&tmp<='z'){
                    input[i][j] = tmp-'a'+1;
                    allWeight += tmp-'a'+1;
                }else{
                    input[i][j] = tmp-'A'+27;
                    allWeight += tmp-'A'+27;
                }
            }
        }

        int mstWeight = 0;
        ex(0);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if (!isVisited[node.getLinkedNodeIdx()]){
                mstWeight+=node.getWeight();
                ex(node.getLinkedNodeIdx());
            }
        }

        for (int  i =0 ;i<N; i++){
            if (!isVisited[i]){
                System.out.println(-1);return;
            }
        }
        System.out.println(allWeight-mstWeight);
    }
    public void ex(int idx){
        isVisited[idx] = true;
        for(int i = 0; i<N; i++){
            if (i==idx)continue;
            if (!isVisited[i]){
                if (input[i][idx]!=0)queue.add(new Node(i, input[i][idx]));
            }
        }
        for (int j = 0; j<N; j++){
            if (j==idx)continue;
            if (!isVisited[j]){
                if (input[idx][j]!=0)queue.add(new Node(j, input[idx][j]));
            }
        }
    }
}
class Node{
    private final int linkedNodeIdx;
    private final int weight;

    public Node(int linkedNodeIdx, int weight) {
        this.linkedNodeIdx = linkedNodeIdx;
        this.weight = weight;
    }

    public int getLinkedNodeIdx() {
        return linkedNodeIdx;
    }

    public int getWeight() {
        return weight;
    }
}
class Buf{
    BufferedReader br;
    StringTokenizer st;
    Buf(){
        br= new BufferedReader(new InputStreamReader(System.in));
    }
    String next(){
        while(st==null||!st.hasMoreElements()){
            try{
                st= new StringTokenizer(br.readLine());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt(){
        return Integer.parseInt(next());
    }

    long newLong(){
        return Long.parseLong(next());
    }
}
