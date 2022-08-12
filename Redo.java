import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Redo {
	public static void main(String[] args) {
		try {
			new Log();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class Log {
	private int commandNum;	
	private ArrayList<Command> log;
	private StringBuilder currentInput;
	public Log() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		commandNum = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		currentInput = new StringBuilder("");
		log = new ArrayList<>();
		for(int i = 0; i< commandNum; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if(s.equals("type")) {

				String s1 = st.nextToken();
				String s2 = st.nextToken();
				Command newCommand = new Command(currentInput.append(s1).toString(), Long.parseLong(s2));
				log.add(newCommand);
			}
			else if(s.equals("undo")) {
				long undoTime = Long.parseLong(st.nextToken());
				long logTime = Long.parseLong(st.nextToken());
				for(int idx = i-1; idx>=0&&log.get(idx).getLogTime()>=(logTime-undoTime); idx--) {
					if(idx==0) {
						currentInput = new StringBuilder();
					}
					else {
						currentInput = new StringBuilder(log.get(idx-1).getLog().toString());
					}
				}
				Command newCommand = new Command(currentInput.toString(), logTime);
				log.add(newCommand);
			}
		}
		System.out.println(currentInput.toString());
	}
}
class Command {
	private StringBuilder log;
	private long logTime;
	public StringBuilder getLog() {
		return log;
	}
	public void setLog(StringBuilder log) {
		this.log = log;
	}
	public long getLogTime() {
		return logTime;
	}
	public void setLogTime(int logTime) {
		this.logTime = logTime;
	}
	Command(String s, long time){
		log = new StringBuilder(s);
		this.logTime = time;
	}
}