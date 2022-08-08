	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.StringTokenizer;
	
	public class ConnectingNumber {
		public static void main(String[] args) {
			try {
				new Solution();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	class Solution {
		private long n;
		private long k;
		public Solution() throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Long.parseLong(st.nextToken());
			k = Long.parseLong(st.nextToken());
			
			boolean[] check = new boolean[(int)k];
			int digit = (int)Math.log10(n)+1;
			long remainder = n%k;
			int count = 1;
			while(true) {
				if(remainder==0) {
					System.out.println(count);
					break;
				}else count++;
				if(check[(int)remainder]) {
					System.out.print(-1);
					break;
				}else {
					check[(int)remainder] = true;
				}
				remainder*=Math.pow(10,digit);
				remainder +=n;
				remainder %=k;
			}
		}
	}








