import java.util.Scanner;

class Program
{
	public static void	main(String[] args)
	{
		long	values = 0;
		long	esp = 1000000000000000000L;
		int		num;
		long	tmp;
		String	week;
		Scanner	kb = new Scanner(System.in);

		System.out.print("->");
		week = kb.nextLine();
		for (int i = 1; i <= 18 && !week.equals("42"); i++)
		{
			num = 0;
			tmp = 10;
			if (!week.equals( "Week " + i))
			{
				System.out.println(week);
				System.err.println("IllegalArgument");
				System.exit(-1);
			}
			System.out.print("->");
			for (int j = 0; j < 5; j++)
			{
				num = kb.nextInt();
				if (num < tmp)
					tmp = num;
			}
			values += tmp * esp;
			esp /= 10;
			System.out.print("->");
			kb.nextLine();
			week = kb.nextLine();
		}
		esp = 1000000000000000000L;
		for (int i = 1; i <= 18 && values / esp != 0; i++)
		{
			System.out.print("Week " + i + " ");
			tmp = values / esp;
			values -= tmp * esp;
			esp /= 10;
			for (int j = 0; j < tmp; j++)
				System.out.print("=");
			System.out.println(">");
		}
	}
}

