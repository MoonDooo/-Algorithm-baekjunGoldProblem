import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try{
            new NumberCard();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
class NumberCard{
    public int[][] cin;
    public String num;
    public NumberCard() throws IOException {
        cin = new int[40][2];
        cin[0][0] = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = br.readLine();

        logic(num, 1);

        System.out.println(cin[num.length()-1][0]+cin[num.length()-1][1]);
    }

    public void logic(String num, int idx){
        if(num.length()<2)return;
        int n = Integer.parseInt(num.substring(0,2));
        if(n==0)return;
        if(0<n%10){ 
            cin[idx][0] = cin[idx-1][0] + cin[idx-1][1];
        }
        if( 10<= n && n <=34){
            cin[idx][1] = cin[idx-1][0];
        }
        logic(num.substring(1), ++idx);
    }

}
