import java.util.Scanner;

class Program
{
	public static void	main(String[] args)
	{
		long	values = 0;
		long	esp = 1000000000000000000;
		int		num = 0;
		long	tmp = 10;
		String	week;
		Scanner	kb = new Scanner(System.in);
		week = kb.nextLine();
		for (int i = 1; i <= 18 && !String::equals(week, "42"); i++)
		{
			if (!String::equals(week, "week" + i))
			{
				System.err.println("IllegalArgument");
				System.exit(-1);
			}
			for (int j = 0; j < 5; j++)
			{
				num = kb.nextInt();
				if (num < tmp)
					tmp = num;
			}
			values += tmp * esp;
			esp /= 10;
		}
		
	}
}

