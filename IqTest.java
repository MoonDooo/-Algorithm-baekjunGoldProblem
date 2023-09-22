import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * author: MoonDooo
 * 백준 #1111 IqTest
 */
public class Main {
    public static void main(String[] args) {
        new IqTest();
    }
}
class IqTest{
    Buf buf = new Buf();
    int N;
    int[] input;
    public IqTest() {
        initData();

        if(N == 1){
            System.out.println("A");return;
        }
        if(N==2){
            if(input[1]==input[0]){
                System.out.println(input[0]);
            }else{
                System.out.println("A");
            }
            return;
        }

        int a, b;
        if(input[0]!=input[1]){
            a = (input[2]-input[1])/(input[1]-input[0]);
        } else{
            a = 0;
        }
        b = input[1]-a*input[0];

        for(int i =0; i<N-1; i++){
            if(input[i]*a+b != input[i+1]){
                System.out.println("B");return;
            }
        }
        System.out.println(input[N-1]*a+b);
    }

    private void initData() {
        N = buf.nextInt();
        input = new int[N];
        for(int i = 0; i<N; i++){
            input[i] = buf.nextInt();
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

}
