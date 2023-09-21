import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 *  author: MoonDooo
 *  백준 1099 알수없는 문장
 */
public class Main {
    public static void main(String[] args) {
        new unknownSentence();
    }
}
class unknownSentence{
    private String input;
    private int wordNum;
    private String[] wordList;
    private int[] costList;
    private final Buf buf = new Buf();

    public unknownSentence(){
        initData();
        for(int i = 0; i<input.length(); i++){
            for(int j = 0; j<wordNum; j++){
                if(wordList[j].length()<=i+1){
                    int preCost;
                    if (i-wordList[j].length()<0)preCost=0;
                    else preCost = costList[i-wordList[j].length()];
                    if(preCost==-1){
                        continue;
                    }
                    int cost = isCanMake(input.substring(i+1-wordList[j].length(),i+1), wordList[j]);
                    
                    if(cost!=-1){
                        if (costList[i]==-1)costList[i]=cost+preCost;
                        else if (cost+preCost < costList[i])costList[i]=cost+preCost;
                    }
                }
            }
        }
        System.out.println(costList[input.length()-1]);
    }
    private int isCanMake(String s1, String s2){
        if(s1.length()!=s2.length())return -1;
        int[] s1Alp = new int[26];
        int[] s2Alp = new int[26];
        s1.chars().forEach(c->s1Alp[c-97]++);
        s2.chars().forEach(c->s2Alp[c-97]++);
        if (!Arrays.equals(s1Alp, s2Alp))return -1;
        int cost = 0;
        for(int i =0; i<s1.length(); i++){
            if(s1.charAt(i)!=s2.charAt(i))cost++;
        }

        return cost;
    }

    private String initData() {
        input = buf.next();
        wordNum = buf.nextInt();
        wordList = new String[wordNum];
        for(int i = 0; i<wordNum; i++){
            wordList[i] = buf.next();
        }
        costList = new int[input.length()];
        Arrays.fill(costList, -1);
        return input;
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
