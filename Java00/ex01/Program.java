import java.util.Scanner;

class Program {
	public static void main(String[] args) {
		int		num;
		int		ops = 0;
		boolean	isPrime = true;
		Scanner	kb = new Scanner(System.in);

		System.out.print("->");
		num = kb.nextInt();
		if (num <= 1)
		{
			System.err.println("IllegalArgument");
			System.exit(-1);
		}
		ops++;
		if (num % 2 == 0 && num == 2) {
			isPrime = true;
		}
		else if (num % 2 == 0) {
			isPrime = false;
		}
		else {
			for (int i = 3; isPrime && i <= num / i; i += 2) {
				if (++ops > 0 && num % i == 0) {
					isPrime = false;
				}
			}
		}
		System.out.println(isPrime + " " + ops);
	}
}
