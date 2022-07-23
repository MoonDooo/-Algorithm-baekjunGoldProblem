import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//백준 1068번 문제
public class TreeLeafNode {
	public static void main(String[] args) {
		try {
			new Tree();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class Tree{
	private int treeSize;
	private Node[] tree;
	private int deleteNode;
	private int leafNodeCounter;
	private boolean[] isAlreadyVisit;
	private int root;
	public Tree() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		treeSize = Integer.parseInt(br.readLine());
		tree = new Node[treeSize];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] tmpBrArray = new int[treeSize];
		for(int i = 0; i<treeSize; i++) {
			tmpBrArray[i]=Integer.parseInt(st.nextToken());
			Node newNode = new Node(i);
			tree[i] = newNode;
		}
		root =0;
		for(int i = 0; i<treeSize; i++) {
			if(tmpBrArray[i]==-1) {
				root = i;
			}
			else {
				tree[i].setParent(tree[tmpBrArray[i]]);
				tree[tmpBrArray[i]].addChildNode(tree[i]);
			}
		} 
		deleteNode = Integer.parseInt(br.readLine());
		leafNodeCounter = 0;
		isAlreadyVisit = new boolean[treeSize];
		DeleteNode(deleteNode);
		Dfs(tree[root]);
		if(root == deleteNode) {
			System.out.println(0);
		}
		else {
			System.out.println(leafNodeCounter);
		}
	}
	
	public void DeleteNode(int deleteNode) {
		if(deleteNode==root) {
			tree[root].getChildNode().clear();
		}
		else {
			tree[deleteNode].getChildNode().clear();
			tree[deleteNode].getParent().getChildNode().remove(tree[deleteNode]);
		}
	}
	
	public void Dfs(Node node) {
		isAlreadyVisit[node.getElement()] = true;
		if(node.getChildNode().size()==0) {
			leafNodeCounter++;
		}
		for(int i = 0; i<node.getChildNode().size(); i++) {
			if(!isAlreadyVisit[node.getChildNode().get(i).getElement()]) {
				Dfs(node.getChildNode().get(i));
			}
		}
	}
}
class Node{ 	
	private int element;
	private Node parent;
	private ArrayList<Node> childNode;
	
	public Node(int element) {
		childNode = new ArrayList<Node>();
		this.element = element;
	}
	public int getElement() {
		return element;
	}
	public void setElement(int element) {
		this.element = element;
	}
	public ArrayList<Node> getChildNode() {
		return childNode;
	}

	public void addChildNode(Node newNode) {
		childNode.add(newNode);
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
}

















