import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new FillingBox();
    }
}
class FillingBox{
    private final Buf buf = new Buf();
    private int[] cubes;
    private int initLength, initWidth, initHeight;
    private int N;
    private int result;
    public FillingBox(){
        initData();
        calculating(initLength, initWidth, initHeight,  N-1);
        System.out.println(result);
    }

    private void calculating(int length, int width, int height, int n) {

        if (length==0||width==0||height==0)return;
        else if (n<0){
            System.out.println(-1);
            System.exit(0);
        }
        if (cubes[n]<=0){
            calculating(length,width,height,n-1);
            return;
        }
        int tmp = 1<<n;
        if (length<tmp||width<tmp||height<tmp){
            calculating(length, width, height, n-1);
        }else{
            result++;
            cubes[n]--;
            calculating(tmp, width-tmp, tmp, n);
            calculating(length-tmp, width, tmp, n);
            calculating(length, width, height-tmp, n);
        }
    }


    private void initData() {
        result = 0;
        initLength = buf.nextInt();
        initWidth = buf.nextInt();
        initHeight = buf.nextInt();
        N = buf.nextInt();
        cubes = new int[N];
        for(int i = 0; i<N; i++){
            cubes[buf.nextInt()] = buf.nextInt();
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
