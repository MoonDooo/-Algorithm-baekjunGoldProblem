//백준 1025번
import java.util.Scanner;
class FindPerfectSquareNumber {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NumberList();
	}	
}
class NumberList{
	Scanner sc = new Scanner(System.in);
	private int listRowHeight;
	private int listColumnWidth;
	private int[][] list;
	private int result = -1;
	
	public NumberList() {
		ScanListSize();
		CreateArray();
		FindMaxPerfectSquareNumber();
		System.out.println(result);
	}
	
	public void ScanListSize() {
		listRowHeight = sc.nextInt();
		listColumnWidth = sc.nextInt();
	}
	
	public void CreateArray() {
		list = new int[listColumnWidth][listRowHeight];
		for(int i = 0; i<listRowHeight; i++) {
			InsertNumberIntoList(i);
		}
	}
	
	public void InsertNumberIntoList(int RowNumber) {
		int RowNumberLine = sc.nextInt();
		for(int i = 0; i<listColumnWidth; i++) {
			list[i][RowNumber] = RowNumberLine%10;
			RowNumberLine = RowNumberLine/10;
		}
	}
	
	public void FindMaxPerfectSquareNumber() {
		for(int i = 0; i < listColumnWidth; i++) {
			for(int j = 0; j < listRowHeight; j++) {
				AP(i,j);
			}
		}
	}
	
	public void AP(int firstColumnNumber, int firstRowNumber) {
		int columnNumber = firstColumnNumber;
		int rowNumber = firstRowNumber;
		int assosiatedNumber;
		for(int i = -firstColumnNumber; i <= listColumnWidth; i++) {
			for(int j = -firstRowNumber; j <= listRowHeight; j++) {
				if(i==0&&j==0)continue;
				columnNumber = firstColumnNumber;
				rowNumber = firstRowNumber;
				assosiatedNumber = 0;
				while(CheckedListColumnAndRow(rowNumber, columnNumber, i, j)) {
					assosiatedNumber *= 10;
					assosiatedNumber += list[columnNumber][rowNumber];
					
					if(isPerfectSquareNumber(assosiatedNumber)) {
						result = Math.max(result, assosiatedNumber);  
					}
					
					columnNumber += i;
					rowNumber += j;
				}
			}
		}
	}
	public boolean CheckedListColumnAndRow(int Row, int Col, int i, int j) {
		return (0 <= Row && 0 <= Col && Row < listRowHeight && Col < listColumnWidth);	
	}
	public boolean isPerfectSquareNumber(int num){
		return Math.sqrt(num)%1==0?true:false;
	}
}






