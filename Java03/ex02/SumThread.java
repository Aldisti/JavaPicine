import java.util.*;

class SumThread extends Thread {
	private int[]	numbers;
	private int[]	output;
	private int		index;
	private int		start;
	private int		end;

	public	SumThread(int[] nums, int[] out, int i, int s, int e) {
		this.numbers = nums;
		this.output = out;
		this.index = i;
		this.start = s;
		this.end = e;
	}

	public void	run() {
		int	s = this.start;
		while (s < this.end && s < this.numbers.length) {
			this.output[this.index] += this.numbers[s++];
		}
		if (this.index != this.output.length - 1) {
			System.out.println("Thread " + (this.index + 1) + ": from "
				+ this.start + " to " + this.end + " sum is " + this.output[this.index]);
		}
		else {
			System.out.println("SumThread by threads: " + this.output[this.index]);
		}
	}
}
