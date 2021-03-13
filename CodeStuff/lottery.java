import java.util.*;

// 1 ~ n 사이의 수에서 중복되지 않는 m개의 수를 랜덤으로 뽑기!
public class LotteryDrawing {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("How may numbers to draw ? ");
		int k = in.nextInt();
		
		System.out.print("Input the maximum number! ");
		int r = in.nextInt();
		
		int samplespace[] = new int[r];
		for(int i=0 ; i<r; ++i) samplespace[i] = i+1;
		int drawspace[] = new int[k];
		
		Draw(samplespace, drawspace);
		
		for(int i : drawspace) System.out.print(i + " ");
		System.out.println();
		
	}
	
	public static void Draw(int []sampleSpace, int[] drawSpace) {
		
		int r = sampleSpace.length;
		
		for(int i=0; i<drawSpace.length; ++i) {
			int tmp = (int)(Math.random() * r);
			drawSpace[i] = sampleSpace[tmp];
			sampleSpace[tmp] = sampleSpace[(r--)-1];
		}
	
	}
}
