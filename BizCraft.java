import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1301 비즈 공예
 * MoonDoo
 */
public class Main {
    public static void main(String[] args) {
        new BizCraft();
    }
}
class BizCraft{
    private final Buf buf = new Buf();
    private final HashMap<Craft,Long> dp = new HashMap<>();
    private int N;
    int[] input;

    public BizCraft(){
        N = buf.nextInt();
        input = new int[N];
        for (int i = 0; i<N; i++){
            input[i] = buf.nextInt();
        }

        System.out.println(dp(new Craft(input, -1, -1)));
    }
    public long dp(Craft craft){
        if (craft.getRemainbiz().isZero())return 1;
        if (dp.get(craft) != null){
            return dp.get(craft);
        }
        long tmp = 0;
        for(int i = 0; i<N; i++){
            if (craft.getOne()!=i&&craft.getTwo()!=i&&0<craft.getRemainbiz().getCount()[i]){
                craft.getRemainbiz().getCount()[i]--;
                tmp+=dp(new Craft(craft.getRemainbiz().getCount(), craft.getTwo(), i));
                craft.getRemainbiz().getCount()[i]++;
            }
        }
        dp.put(craft,tmp);
        return tmp;
    }
}
class Craft{
    private final Biz remainbiz;
    private int one, two;

    public Craft(int[] array, int one, int two) {
        this.remainbiz = new Biz(array);
        this.one = one;
        this.two = two;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Craft))return false;
        Craft c = (Craft) obj;
        return Arrays.equals(this.remainbiz.getCount(), c.remainbiz.getCount())&&this.one==c.one&&this.two==c.two;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(remainbiz.getCount()), one, two);
    }

    public Biz getRemainbiz() {
        return remainbiz;
    }

    public int getOne() {
        return one;
    }

    public int getTwo() {
        return two;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public void setTwo(int two) {
        this.two = two;
    }
}
class Biz{
    private int[] count;

    public Biz(int[] array) {
        this.count = array;
    }

    public int[] getCount() {
        return count;
    }

    public boolean isZero(){
        return Arrays.stream(count).allMatch(c -> c == 0);

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
