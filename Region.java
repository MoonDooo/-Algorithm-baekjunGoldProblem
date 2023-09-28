import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 1304번 지역
 * author : MoonDooo
 */
public class  Main {
    public static void main(String[] args) {
        new Region();
    }
}
class Region{
    private final Buf buf = new Buf();
    private int N, M;
    private int[] isLinked;
    public Region() {
        initData();
        for (int i = 1; 2*i<=N; i++){
            if (N%i!=0)continue;
            boolean b= false;
            for (int j = 0; i*j<N; j++){
                for (int k = j; k<(j+1)*i; k++){
                    if ((j+1)*i<=isLinked[k]){
                        b = true;
                        break;
                    }
                }
                if (b)break;
            }
            if (!b){
                System.out.println(N/i);
                return;
            }
        }
        System.out.println(1);
    }

    private void initData() {
        N = buf.nextInt();
        M = buf.nextInt();
        isLinked = new int[N];
        for(int i = 0; i<M; i++){
            int a = buf.nextInt()-1;
            int b = buf.nextInt()-1;
            if (b<a){
                for (int j = b; j<a;j++){
                    if (isLinked[j]<a) isLinked[j] = a;
                }
            }
        }
    }
}
class Buf{
    BufferedReader br;
    StringTokenizer st;
    Buf(){
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    String next(){
        while(st==null||!st.hasMoreElements()){
            try{
                st= new StringTokenizer(br.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt(){
        return Integer.parseInt(next());
    }
}

/**
 * 원래 코드 ( 틀린 코드 )
 */
/*
class Region{
    private final Buf buf = new Buf();
    private int N, M;
    private Node[] nodes;
    private boolean[][] isLinked;
    public Region(){
        initData();
        for (int i = 0; i<N; i++){
            dfs(i, i);
        }

        int tmp = 5000;
        for (int i = 1; i<=N/2; i++){
            if (N%(i)!=0)continue;
            boolean b = false;
            for (int j = i-1; j < N; j+=i){
                for (int k = j+1; k<N; k++){
                    if (isLinked[k][j]){
                        b = true;
                        break;
                    }
                }
                if (b)break;
            }
            if (!b){
                tmp = i;
            }
         }
        if (N/tmp==0) System.out.println(1);
        else System.out.println(N/tmp);
    }

    public void dfs(int startNodeIdx, int nodeIdx){
        isLinked[startNodeIdx][nodeIdx] = true;
        Node node = nodes[nodeIdx];
        for (int i = 0; i<node.getLinkedNode().size(); i++){
            if (!isLinked[startNodeIdx][node.getLinkedNode().get(i)]){
                dfs(startNodeIdx, node.getLinkedNode().get(i));
            }
        }
    }
    private void initData() {
        N = buf.nextInt();
        M = buf.nextInt();
        isLinked = new boolean[N][N];
        nodes=  new Node[N];
        nodes[0] = new Node();
        for (int i = 1; i<N; i++){
            nodes[i] = new Node();
            nodes[i-1].addLinkedNode(i);
        }
        for(int i = 0; i<M; i++){
            int a = buf.nextInt()-1;
            int b = buf.nextInt()-1;
            nodes[a].addLinkedNode(b);
        }
    }
}
class Buf{
    BufferedReader br;
    StringTokenizer st;
    Buf(){
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    String next(){
        while(st==null||!st.hasMoreElements()){
            try{
                st= new StringTokenizer(br.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt(){
        return Integer.parseInt(next());
    }
}
class Node{
    private final List<Integer> linkedNodeIdx = new ArrayList<>();

    public List<Integer> getLinkedNode() {
        return linkedNodeIdx;
    }

    public void addLinkedNode(Integer nodeIdx){
        this.linkedNodeIdx.add(nodeIdx);
    }
}
 */
