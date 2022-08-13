	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
	
	public class LectureRoom {
		public static void main(String[] args) {
			try {
				new FindMinLectureRoomNum();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	class FindMinLectureRoomNum{
		private int lectureNum;
		private ArrayList<Lecture> lectureArray;
		private PriorityQueue<Integer> queue;
		private int result;
		public FindMinLectureRoomNum() throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			queue = new PriorityQueue<>();
			lectureArray = new ArrayList<>();

			lectureNum = Integer.parseInt(br.readLine());
			
			StringTokenizer st;
			
			for(int i = 0; i<lectureNum; i++) {
				st = new StringTokenizer(br.readLine());
				int inputLectureIdx = Integer.parseInt(st.nextToken());
				int inputStartTime = Integer.parseInt(st.nextToken());
				int inputEndTime = Integer.parseInt(st.nextToken());
				lectureArray.add(new Lecture(inputLectureIdx, inputStartTime, inputEndTime));
			}
			
			Collections.sort(lectureArray, new Comparator<Lecture>() {
				@Override
				public int compare(Lecture o1, Lecture o2) {
					return Integer.compare(o1.getStartTime(), o2.getStartTime());
				}
				
			});
			
			
			result = Integer.MIN_VALUE;
			for(int i = 0; i<lectureNum; i++) {
				while(!queue.isEmpty()&&queue.peek() <= lectureArray.get(i).getStartTime()) {
					System.out.println(queue.poll());
				}
				System.out.println("큐에 삽입됨  : > " + lectureArray.get(i).getEndTime());
				queue.add(lectureArray.get(i).getEndTime());
				result = Math.max(result, queue.size());
			}
			System.out.println(result);
		}
		
	}
	
	class Lecture{
		private int lectureIdx;
		private int startTime;
		private int endTime;
		public int getLectureIdx() {
			return lectureIdx;
		}
		public void setLectureIdx(int lectureIdx) {
			this.lectureIdx = lectureIdx;
		}
		public int getStartTime() {
			return startTime;
		}
		public void setStartTime(int startTime) {
			this.startTime = startTime;
		}
		public int getEndTime() {
			return endTime;
		}
		public void setEndTime(int endTime) {
			this.endTime = endTime;
		}
		public Lecture(int lectureIdx, int startTime, int endTime) {
			this.lectureIdx = lectureIdx;
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}
	
	

















