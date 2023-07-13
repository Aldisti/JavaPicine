import java.util.*;

class Sum extends Thread {
	private int[]	numbers;
	private int[]	output;
	private int		index;
	private int		start;
	private int		end;

	public	Sum(int[] nums, int[] out, int i, int s, int e) {
		this.numbers = nums;
		this.output = out;
		this.index = i;
		this.start = s;
		this.end = e;
	}

	public void	run() {
		while (this.start < this.end && this.start < this.numbers.length) {
			this.output[this.index] += this.numbers[this.start++];
		}
	}
}
