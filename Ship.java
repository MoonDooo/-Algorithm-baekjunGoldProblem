import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @백준 1092
 * @author MoonDooo
 */
public class Ship {
	public static void main(String[] args) {
		try {
			new MinTimeToShip();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class MinTimeToShip {
	int craneNum;
	int boxNum;
	int[] crane;
	int[] box;
	int time;
	public MinTimeToShip() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		craneNum = Integer.parseInt(br.readLine());
		StringTokenizer craneSt = new StringTokenizer(br.readLine());
		crane = new int[craneNum];
		for(int i = 0; i< craneNum;i++){
			crane[i] = Integer.parseInt(craneSt.nextToken());
		}
		boxNum = Integer.parseInt(br.readLine());
		StringTokenizer boxSt = new StringTokenizer(br.readLine());
		box = new int[boxNum];
		for(int i = 0 ; i < boxNum; i++) {
			box[i] = Integer.parseInt(boxSt.nextToken());
		}
		
		time = 0;
		sort(box);
		sort(crane);
		
		if(box[0] > crane[0]) {
			System.out.println(-1);
			return;
		}
		
		boolean isRemainBox = true;
		while(isRemainBox) {
			isRemainBox = false;
			int boxThatSmallerThanCrane = 0;
			int indexOfCrane = 0;
			while(indexOfCrane<craneNum&&boxThatSmallerThanCrane<boxNum) {
				if(box[boxThatSmallerThanCrane]<=crane[indexOfCrane]&&box[boxThatSmallerThanCrane]!=-1) {
					box[boxThatSmallerThanCrane] = -1;
					indexOfCrane++;
					boxThatSmallerThanCrane++;
				}
				else{
					boxThatSmallerThanCrane++;
				}
			}
			time++;
			for(int i = 0; i<boxNum; i++) {
				if(box[i]!=-1) {
					isRemainBox=true;
					break;
				}
			}
		}
		System.out.println(time);
	}
	
	public void sort(int[] array) {
		quickSort(array, 0, array.length-1);
	}
	public void quickSort(int[] array, int start, int end) {
		int part = partition(array,start,end);
		if(part-1>start)quickSort(array, start, part-1);
		if(part<end)quickSort(array, part, end);
	}
	public int partition(int[] array, int start, int end) {
		int pivot = array[(start+end)/2];
		while(start<=end) {
			while(array[start]>pivot)start++;;
			while(array[end]<pivot)end--;
			if(start<=end) {
				swap(array, start, end);
				start++; end--;
			}
		}
		return start;
	}
	public void swap(int[] array, int a, int b) {
		int tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
}














