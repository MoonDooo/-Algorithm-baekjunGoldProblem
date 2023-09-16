import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new FractalPlane();
    }
}

class FractalPlane{
    int S;
    int N;
    int K;
    int R1, R2, C1, C2;



    public FractalPlane() throws IOException {
        inputData();
        boolean result[][] = new boolean[C2-C1+1][R2-R1+1];
        for(int i = C1; i<=C2; i++){
            for(int j = R1; j<=R2; j++){
                result[i-C1][j-R1] = isMiddle(i,j, S);
            }
        }

        for(int i =0; i<(R2-R1+1); i++){
            for(int j = 0; j<(C2-C1+1); j++){
                if(result[j][i]){
                    System.out.print(1);
                }else{
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }

    private boolean isMiddle(long i, long j, long s) {
        if (s<=0)return false;
        long pow = (long) Math.pow(N,s-1);
        if(range(N*pow, K*pow, i, j)){
            return true;
        }else{
            return isMiddle(i%pow, j%pow, s-1);
        }
    }

    private void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());
    }


    public boolean range(long N, long K, long x, long y){
        return (N%2==0)?evenRange(N,K,x)&&evenRange(N,K,y):oddRange(N,K,x)&&oddRange(N,K,y);
    }

    private boolean oddRange(long N, long K, long i) {
        return N/2-K/2<=i&&i<=N/2+K/2;
    }

    public boolean evenRange(long N, long K, long i){
        return N/2 - K/2 <= i&&i<=N/2 + K/2-1;
    }
}
