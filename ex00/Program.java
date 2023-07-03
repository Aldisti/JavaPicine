










public class Program
{
	public static void main(String[] args)
	{
		int	num = 479598;
		int	sum = 0;

		for (int i = 0; num > 0; i++)
		{
			sum += num % 10;
			num /= 10;
		}
		System.out.println(sum);
	}
}

