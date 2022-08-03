import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Kickdown {
	public static void main(String[] args) {
		try{
			new minSizeGearWhenKickdown();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class minSizeGearWhenKickdown{
	public static final byte STRING_TO_BYTE = 48;
	private byte[] bottomGear;
	private byte[] topGear;
	private int minSize;
	public minSizeGearWhenKickdown() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		topGear = br.readLine().getBytes();
		bottomGear = br.readLine().getBytes();
		
		minSize = topGear.length + bottomGear.length;
		for(int i = -bottomGear.length; i<topGear.length; i++) {
			boolean isCanMakeKickDown = true;
			int j;
			for(j = 0; j < topGear.length&&j<bottomGear.length&&j < (bottomGear.length+i)&&j < (topGear.length-i); j++) {
				if(i<0) {
					if(topGear[j]-STRING_TO_BYTE==2&&bottomGear[bottomGear.length-(bottomGear.length+i)+j]-STRING_TO_BYTE==2) {
						isCanMakeKickDown = false;
						break;
					}
				}
				else {
					if(bottomGear[j]-STRING_TO_BYTE==2&&topGear[i+j]-STRING_TO_BYTE==2) {
						isCanMakeKickDown = false;
						break;
					}
				}
			}
			if(isCanMakeKickDown) {
				minSize = Math.min( minSize, bottomGear.length+topGear.length-j);
			}
		}
		bw.write(minSize+"");
		bw.flush();
		br.close();
		bw.close();
	}
}








