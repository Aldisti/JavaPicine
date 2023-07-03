import java.util.Scanner;

class Program
{
	public static int sumOfDigits(int num)
	{
		int	sum = 0;

		while (num > 0)
		{
			sum += num % 10;
			num /= 10;
		}
		return (sum);
	}

	public static boolean	isPrime(int num)
	{
		int		ops = 0;
		boolean	isPrime = true;

		if (num <= 1)
		{
			System.err.println("IllegalArgument");
			System.exit(-1);
		}
		ops++;
		if (num % 2 == 0 && num == 2)
			isPrime = true;
		else if (num % 2 == 0)
			isPrime = false;
		else
			for (int i = 3; isPrime && i <= num / i; i += 2)
				if (++ops > 0 && num % i == 0)
					isPrime = false;
		return (isPrime);
	}	

	public static void	main(String[] args)
	{
		int	num = 0;
		int	count = 0;
		Scanner	kb = new Scanner(System.in);

		while (num != 42)
		{
			System.out.print("->");
			num = kb.nextInt();
			if (isPrime(sumOfDigits(num)))
				count++;
		}
		System.out.println("Count of coffee-request - " + count);
	}
}

