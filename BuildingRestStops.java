#1477
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    int N, M, L;
    private final Buf buf = new Buf();
    int[] input;
    public Sol(){
        N = buf.readInt();
        M = buf.readInt();
        L = buf.readInt();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i<N; i++){
            q.add(buf.readInt());
        }
        int tmp = 0;
        input = new int[N+1];
        for (int i = 0; i<N+1; i++){
            if (i==N){
                input[N] = L-tmp;
            }else{
                int poll = q.poll();
                input[i] = poll - tmp;
                tmp = poll;
            }
        }
        int right = L;
        int left = 1;

        while(left<=right){
            int mid = (right+left)/2;
            if (isCan((mid))){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        System.out.println(left);
    }
    public boolean isCan(int mid){
        int idx = 0;
        for (int i = 0; i<=N; i++){
            idx+=(input[i]/mid);
            if (input[i]%mid==0)idx--;
        }
        return M<idx;
    }
}
class Buf{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    public String read(){
        while(st==null||!st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            }catch(IOException e){
                return null;
            }
        }
        return st.nextToken();
    }
    public Integer readInt(){
        return Integer.parseInt(read());
    }

    public Long readLong(){
        return Long.parseLong(read());
    }
}
