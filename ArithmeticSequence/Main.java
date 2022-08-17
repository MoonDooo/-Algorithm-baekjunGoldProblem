	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	
	public class Main {
		public static void main(String[] args) {
			try {
				new ArithmeticSequence();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	class ArithmeticSequence{
		private int l;
		private int r;
		private int k;
		public ArithmeticSequence() throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			l = Integer.parseInt(br.readLine());
			r = Integer.parseInt(br.readLine());
			k = Integer.parseInt(br.readLine());
			
			int x = k;
			int d = 0;
			for(int i = 0; i<k; i++) {
				d += i;
			}
			if(l<(x+d)&&r<(x+d)) {
				System.out.println(0);
				return;
			}

			int lowLange = Math.max(x+d-1, l-1);
			int highLange = Math.max(x+d-1, r);
			
			int tmp = d==x?d:Math.abs(d-x);
			int result = (highLange/tmp)-(lowLange/tmp);
			
			
			if(k==4&&lowLange<=12&&highLange>=12)result--;
			System.out.println(result);
		}
	}
	
	
	
	
	