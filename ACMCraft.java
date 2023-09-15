import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new ACMCraft();
    }
}

class ACMCraft{
    private final ArrayList<Long> result = new ArrayList<>();
    private StringTokenizer st;
    public ACMCraft() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());
        for (int i = 0; i< testCaseNum; i++){
            st = new StringTokenizer(br.readLine());
            int nodeNum = Integer.parseInt(st.nextToken());
            int lineNum = Integer.parseInt(st.nextToken());

            Node[] nodes = new Node[nodeNum];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< nodeNum; j++) {
                nodes[j] = new Node(j, Long.parseLong(st.nextToken()));
            }

            for(int j = 0; j< lineNum; j++){
                st = new StringTokenizer(br.readLine());
                int firstIdx = Integer.parseInt(st.nextToken())-1;
                int secondIdx = Integer.parseInt(st.nextToken())-1;
                nodes[secondIdx].plusPre();
                nodes[firstIdx].addConnectedNode(nodes[secondIdx]);
            }

            Queue<Node> nodeQueue = new LinkedList<>();
            int targetNodeIdx = Integer.parseInt(br.readLine())-1;

            for(int j = 0; j<nodeNum; j++){
                if(nodes[j].getPre()==0){
                    nodeQueue.add(nodes[j]);
                    nodes[j].setMax(nodes[j].getDelay());
                }
            }

            while(0<nodes[targetNodeIdx].getPre()){
                Node node = nodeQueue.remove();
                for(int j = 0; j< node.getConnectedNode().size(); j++){
                    Node linkedNode = node.getConnectedNode().get(j);
                    linkedNode.minusPre();
                    if(linkedNode.getPre()==0) nodeQueue.add(linkedNode);
                    linkedNode.setMax(Long.max(node.getMax()+linkedNode.getDelay(), linkedNode.getMax()));
                }
            }
    
            result.add(nodes[targetNodeIdx].getMax());
        }
        for(Long result : result){
            System.out.println(result);
        }
    }


}


class Node{
    private int num;
    private Long delay;
    private final ArrayList<Node> connectedNode = new ArrayList<>();
    private Long max;
    private int pre;
    public Node(int num, Long delay) {
        this.num = num;
        this.delay = delay;
        this.max = 0L;
        this.pre = 0;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public ArrayList<Node> getConnectedNode() {
        return connectedNode;
    }

    public void addConnectedNode(Node node){
        connectedNode.add(node);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getMax() {
        return max;
    }

    public int getPre() {
        return pre;
    }

    public void plusPre() {
        this.pre++;
    }
    public void minusPre(){
        this.pre--;
    }
}

/*class ACMCraft{
    public ACMCraft() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());
        ArrayList<Long> result = new ArrayList<>();
        for (int i = 0; i< testCaseNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nodeNum = Integer.parseInt(st.nextToken());
            int lineNum = Integer.parseInt(st.nextToken());

            Long[] dp = new Long[nodeNum];
            int[] node = new int[nodeNum];
            int[] lineNumByNode = new int[nodeNum];
            Line[] lines = new Line[nodeNum];

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<nodeNum; j++){
                int num = Integer.parseInt(st.nextToken());
                node[j] = num;
                dp[j] = (long) num;
                lines[j] = new Line();
            }


            for(int j = 0; j<lineNum; j++){
                st = new StringTokenizer(br.readLine());
                int startIdx = Integer.parseInt(st.nextToken())-1;
                int endIdx = Integer.parseInt(st.nextToken())-1;
                lineNumByNode[endIdx]++;
                lines[startIdx].linkedNode.add(endIdx);
            }

            int targetIdx = Integer.parseInt(br.readLine())-1;

            while(lineNumByNode[targetIdx]!=0){
                for(int j = 0; j<nodeNum; j++){
                    if(lineNumByNode[j]==0){
                        for(int k = 0; k<lines[j].linkedNode.size(); k++){
                            int idx = lines[j].linkedNode.get(k);
                            lineNumByNode[idx]--;
                            dp[idx] = Long.max(dp[j]+node[idx],dp[idx]);
                        }
                    }
                }
            }

            result.add(dp[targetIdx]);
        }
        for(int i = 0; i< result.size(); i++){
            System.out.println(result.get(i));
        }
    }
}
class Line{
    ArrayList<Integer> linkedNode = new ArrayList<>();
}*/
