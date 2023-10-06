import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new CardOrganization();
    }
}
class CardOrganization{
    private final Buf buf = new Buf();
    private int N;
    private int M;
    private boolean[][] isExistList;
    private int result;
    public CardOrganization() {
        initData();
        for (int i = 0; i<N; i++){
            int tmp = 0;
            for (int j = 0; j<N; j++){
                if (i==j)continue;
                int count = 0;
                for (int k = 0; k<M; k++){
                    if (isExistList[j][k]){
                        count++;
                    }
                }
                if (1<count)tmp++;
                else if (1==count){
                    for (int  k = j+1; k<N; k++) {
                        if (Arrays.equals(isExistList[j], isExistList[k])){
                            tmp++;
                            break;
                        }
                    }
                }
            }
            result = Math.min(result, tmp);
        }
        System.out.println(result);
    }
    public void initData(){
        result=Integer.MAX_VALUE;
        N = buf.nextInt();
        M = buf.nextInt();
        isExistList = new boolean[N][M];
        for (int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if (buf.nextInt()!=0) isExistList[i][j] = true;
            }
        }
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
