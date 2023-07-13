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

class Program {
	public static void	main(String[] args) {
		int			sum = 0;
		int			size;
		int			count;
		int[]		numbers;
		int[]		output;
		Vector<Sum>	threads;
		Random		r;

		if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
			System.out.println("Invalid arguments");
			return ;
		}
		size = Integer.valueOf(args[0].substring(args[0].indexOf('=') + 1, args[0].length()));
		count = Integer.valueOf(args[1].substring(args[1].indexOf('=') + 1, args[1].length()));
		if (count > size) {
			System.out.println("Invalid arguments");
			return ;
		}
		r = new Random();
		numbers = new int[size];
		output = new int[count + 1];
		threads = new Vector<Sum>();
		for (int i = 0; i < size; i++) {
			numbers[i] = r.nextInt(1000);
		}
		for (int i = 0; i < count; i++) {
			if (i == count - 1) {
				threads.add(new Sum(numbers, output, i, size / count * i, size));
			}
			else {
				threads.add(new Sum(numbers, output, i, size / count * i, size / count * (i + 1)));
			}
			threads.get(i).start();
		}
		for (int i = 0; i < count; i++) {
			try {
				threads.get(i).join();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		threads.add(new Sum(output, output, count, 0, count));
		threads.get(count).start();
		try {
			threads.get(count).join();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (int i : numbers) {
			sum += i;
		}
		System.out.println("Sum: " + sum);
		for (int i = 0; i < count; i++) {
			System.out.print("Thread " + (i + 1) + ": from " + (size / count * i) + " to ");
			if (i == count - 1) {
				System.out.print(size);
			}
			else {
				System.out.print((size / count * (i + 1)));
			}
			System.out.println(" sum is " + output[i]);
		}
		System.out.println("Sum by threads: " + output[count]);
	}
}
