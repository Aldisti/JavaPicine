import java.util.Scanner;

class Program {
	public static long	pow(long n, int e) {
		long	num = 1;

		for (int i = 0; i < e; i++) {
			num *= n;
		}
		return (num);
	}

	public static void	error() {
		System.err.println("IllegalArgument");
		System.exit(-1);
	}

	public static void	main(String[] args) {
		Scanner	kb = new Scanner(System.in);
		long	values = 0;
		long	num;
		int		tmp;
		String	week;

		System.out.print("->");
		week = kb.nextLine();
		for (int i = 1; i <= 18 && !week.equals("42"); i++) {
			num = 10;
			if (!week.equals( "Week " + i)) {
				error();
			}
			System.out.print("->");
			for (int j = 0; j < 5; j++) {
				tmp = kb.nextInt();
				if (tmp < 1 || tmp > 9) {
					error();
				}
				if (tmp < num) {
					num = tmp;
				}
			}
			values += num * pow(10, i - 1);
			kb.nextLine();
			System.out.print("->");
			week = kb.nextLine();
		}
		for (int i = 1; i <= 18 && values % 10 != 0; i++) {
			System.out.print("Week " + i + " ");
			num = values % 10;
			values /= 10;
			for (int j = 0; j < num; j++) {
				System.out.print("=");
			}
			System.out.println(">");
		}
	}
}

