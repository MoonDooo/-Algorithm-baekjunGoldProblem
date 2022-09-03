import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) {
		try {
			new BindingNumber();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class BindingNumber{
	private int arrayNum;
	private ArrayList<Integer> plusArray;
	private ArrayList<Integer> minusArray;
	private int result;
	
	public BindingNumber() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arrayNum = Integer.parseInt(br.readLine());
		
		plusArray = new ArrayList<>();
		minusArray = new ArrayList<>();
		for(int i = 0; i<arrayNum;i++) {
			int tmp;
			if((tmp = Integer.parseInt(br.readLine()))>0) {
				plusArray.add(tmp);
			}else {
				minusArray.add(tmp);
			}
		}
			
		Collections.sort(plusArray, Collections.reverseOrder());
		Collections.sort(minusArray);
		//맨끝ㅌ의 경우 호가인
		
		result = 0;
		while(plusArray.size()!=0) {
			int a = plusArray.remove(0);
			if(plusArray.size()==0) {
				result += a;
				break;
			}
			int b = plusArray.remove(0);
			result += Math.max(a+b, a*b);
		}
		while(minusArray.size()!=0) {
			int a = minusArray.remove(0);
			if(minusArray.size()==0) {
				result += a;
				break;
			}
			int b = minusArray.remove(0);
			result += a*b;
		}
//		 for(int i = 0; i<arrayNum;) {
//			 if(i==arrayNum-1) {
//				 result+=array.get(arrayNum-1); System.out.println("라스트 값 들어감 : " +array.get(arrayNum-1)); 
//				  break; 
//			 } 
//			 boolean isMultiBiggerThanSum = (array.get(i)+array.get(i+1))<(array.get(i)*array.get(i+1));
//			 if(isMultiBiggerThanSum) { 
//				  result+=array.get(i)*array.get(i+1);
//				  System.out.println("* 값 들어감 : " + array.get(i) + " * " + array.get(i+1) +" >  " + (array.get(i)*array.get(i+1))); i+=2; 
//			 } 
//			 else { 
//				  result+=array.get(i);
//				  System.out.println("+ 값 들어감 : " + array.get(i)); 
//				  i++; 
//			  }
//			  System.out.println(result); 
//		}
		 
		
//		while(array.size()!=0) {
//			int resultMultiMax = Integer.MIN_VALUE;
//			int resultMultiMaxIdx = 0;
//			int resultSumMax = Integer.MIN_VALUE;
//			int resultSumMaxIdx =  0;
//			int tmp = array.remove(0);
//			if(array.size()==0) {
//				result+=tmp;
//				break;
//			}
//			
//			for(int i = 0; i<array.size(); i++) {
//				if(resultMultiMax<tmp*array.get(i)) {
//					resultMultiMax = tmp*array.get(i);
//					resultMultiMaxIdx = i;
//				}
//				if(resultSumMax<tmp+array.get(i)) {
//					resultSumMax = tmp+array.get(i);
//					resultSumMaxIdx = i;
//				}
//			}
//			System.out.println(resultMultiMaxIdx + "  >    " + resultMultiMax + "   :   "  + resultSumMaxIdx + " > " + resultSumMax);
//			if(resultMultiMax<resultSumMax) {
//				result+=resultSumMax;
//				array.remove(resultSumMaxIdx);
//			}else {
//				result+=resultMultiMax;
//				array.remove(resultMultiMaxIdx);
//			}
//			System.out.println(array.toString());
//			System.out.println(result);
//			System.out.println("____________________");
//		}
		System.out.println(result);
	}
}
