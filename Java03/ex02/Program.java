import java.util.*;

class Program {

	public static int[]	genRandArray(int size, int max) {
		Random	r = new Random();
		int[]	arr = new int[size];

		for (int i = 0; i < size; i++) {
			arr[i] = r.nextInt(max);
		}
		return (arr);
	}

	public static void	main(String[] args) {
		int			sum = 0;
		int			size;
		int			count;
		int[]		numbers;
		int[]		output;
		Vector<Sum>	threads;

		if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
			System.out.println("Invalid arguments");
			return ;
		}
		size = Integer.valueOf(args[0].substring(args[0].indexOf('=') + 1, args[0].length()));
		count = Integer.valueOf(args[1].substring(args[1].indexOf('=') + 1, args[1].length()));
		if (count > size || size <= 0 || count <= 0 || size > 2000000) {
			System.out.println("Invalid arguments");
			return ;
		}
		numbers = genRandArray(size, 1000);
		output = new int[count + 1];
		threads = new Vector<Sum>();
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

