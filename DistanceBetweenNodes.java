import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DistanceBetweenNodes {
	public static void main(String[] args) {
		try {
			new FindDistanceBetweenNodes();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

class FindDistanceBetweenNodes{
	private int treeSize;
	private Tree[] tree;
	private boolean[] visitedNode;
	private int distanceNodeToNode;
	private int NumOfFind;
	public static final int InputToArray(int i) {
		return (i-1);
	}
	public FindDistanceBetweenNodes() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		treeSize = Integer.parseInt(st.nextToken());
		NumOfFind = Integer.parseInt(st.nextToken());
		tree = new Tree[treeSize];
		visitedNode = new boolean[treeSize];
		for(int i = 0; i < treeSize; i ++) {
			Tree newNode = new Tree(i);
			tree[i] = newNode;
		}
		
		for(int i = 0; i< treeSize-1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			tree[InputToArray(parent)].addLink(tree[InputToArray(child)]);
			tree[InputToArray(parent)].addDistanceBetweenLinkedNode(distance);
			tree[InputToArray(child)].addLink(tree[InputToArray(parent)]);
			tree[InputToArray(child)].addDistanceBetweenLinkedNode(distance);
		}
		
		for(int i = 0; i<NumOfFind; i++) {
			st = new StringTokenizer(br.readLine());
			int firstNodeTogetdistance = Integer.parseInt(st.nextToken());
			int secondNodeTogetdistance = Integer.parseInt(st.nextToken());
			distanceNodeToNode = 0;
			for(int j = 0; j< treeSize; j++) {
				visitedNode[j] = false;
			}
			System.out.println("--------------");
			findDistance(tree[InputToArray(firstNodeTogetdistance)], tree[InputToArray(secondNodeTogetdistance)]);
		}
	}
	
	public void findDistance(Tree node, Tree nodeToExplore) {
		visitedNode[node.getNode()] = true;
		if(node==nodeToExplore) {
			System.out.println(distanceNodeToNode);
			return;
		}
		for(int i = 0; i<node.getLink().size(); i++) {
			if(!visitedNode[node.getLink().get(i).getNode()]) {
				distanceNodeToNode+=node.getDistanceBetweenLinkedNode().get(i);
				System.out.println("node : " + (node.getNode()+1) + " > " + (node.getLink().get(i).getNode()+1) + " 거리 : " + node.getDistanceBetweenLinkedNode().get(i) + " 거리 합 " + distanceNodeToNode);
				findDistance(node.getLink().get(i), nodeToExplore);
				distanceNodeToNode-=node.getDistanceBetweenLinkedNode().get(i);
			}
		}
	}
	
}

class Tree {
	private int node;
	private ArrayList<Tree> link;
	private ArrayList<Integer> distanceBetweenLinkedNode;
	public Tree(int i) {
		link = new ArrayList<>();
		distanceBetweenLinkedNode = new ArrayList<>();
		node = i;
	}
	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	public ArrayList<Tree> getLink() {
		return link;
	}
	public void addLink(Tree linkedNode) {
		link.add(linkedNode);
	}
	public ArrayList<Integer> getDistanceBetweenLinkedNode() {
		return distanceBetweenLinkedNode;
	}
	public void addDistanceBetweenLinkedNode(int distance) {
		distanceBetweenLinkedNode.add(distance);
	}
}
