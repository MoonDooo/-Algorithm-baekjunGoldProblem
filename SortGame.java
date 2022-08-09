import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SortGame {
	public static void main(String[] args) {
		try {
			new Sort();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class Sort{
	private int arraySize;
	private int flipTime;
	private Node rootNode;
	HashMap<String, Boolean> map;
	Queue<Node> queue;
	
	public Sort() throws IOException{
		map = new HashMap<>();
		queue = new LinkedList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		arraySize = Integer.parseInt(st.nextToken());
		flipTime = Integer.parseInt(st.nextToken());
		
		int[] newArray = new int[arraySize];
		st= new StringTokenizer(br.readLine());
		for(int i = 0; i < arraySize; i++) {
			newArray[i] = Integer.parseInt(st.nextToken());
		}
		rootNode =new Node(newArray,0, -1);
		queue.add(rootNode);
		map.put(Arrays.toString(rootNode.getArray()), true);
		
		while(queue.size()!=0) {
			Node parentNode = queue.poll();
			boolean isSort = isSortArray(parentNode.getArray());
			if(isSort) {
				System.out.println(parentNode.getHeight());
				return;
			}
			for(int i = 0; i <= arraySize-flipTime; i++) {
				if(parentNode.getParentIdx()==i)continue;
				int[] newNodeArray = FlipNodeArray(i, parentNode);
				if(map.getOrDefault(Arrays.toString(newNodeArray),false)) {
					continue;
				}
				else {
					map.put(Arrays.toString(newNodeArray), true);
				}
				Node newNode = new Node(newNodeArray, parentNode.getHeight()+1, i);
				queue.add(newNode);
			}
		}
		System.out.println(-1);
	}	
	public boolean isSortArray(int [] array) {
		int past = -1;
		for(int i = 0; i<arraySize; i++) {
			int pre = array[i];
			if(pre>=past) {
				past = pre;
				continue;
			}
			else return false;
		}
		return true;
	}
	
	public int[] FlipNodeArray(int i, Node node) {
		int[] array = node.getArray().clone();
		int k = i+(flipTime-1);
		while(i<k) {
			swap(array, i, k);
			i++;
			k--;
		}
		return array;
	}
	public void swap(int[] array, int i, int j) {
		int swapTmp = array[i];
		array[i] = array[j];
		array[j] = swapTmp;
	}
}
	
class Node{
	private int[] array;
	private int height;
	private int parentIdx;
	
	public Node(int[] array, int height, int parentIdx) {
		this.array = array;
		this.height = height;
		this.parentIdx = parentIdx;
	}
	
	
	
	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getParentIdx() {
		return parentIdx;
	}

	public void setParentIdx(int parentIdx) {
		this.parentIdx = parentIdx;
	}
}


