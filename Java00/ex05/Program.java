import java.util.Scanner;

class Program {

	private static final int	DAYS = 30;
	private static final int	LESSONS = 5;
	private static final int	STUDENTS = 10;
	private static final int	MAX_INPUT = 10;
	private static final int	DAYS_MAP[] = {1, -1, -1, -1, 2, 5, -1, 4, 6, -1, 3, -1, -1, -1, 0};
	private static final String	WEEK_MAP[] = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};

	public static int	getDayIndex(char letters[]) {
		return (DAYS_MAP[((((int)letters[1] * (int)letters[2]) % 1000) / 10) % 16]);
	}

	public static void	addLesson(int days[][], char c, String line) {
		int	hour = (int)c - '1';
		int	day = getDayIndex(line.toCharArray());

		for (; day < DAYS; day += 7) {
			days[day][hour] = 1;
		}
	}

	public static int	findStudent(String names[], String name) {
		for (int i = 0; i < STUDENTS && names[i] != null; i++) {
			if (names[i].equals(name)) {
				return (i);
			}
		}
		return (-1);
	}

	public static void	sign(int days[][], int index, Scanner kb) {
		int		hour = kb.nextInt() - 1;
		int		day = kb.nextInt() - 1;
		boolean	presence = kb.nextLine().equals(" HERE");
		int		tmp = 2;

		if (presence)
			tmp = 1;
		tmp = tmp << (index * 2 + 1);
		days[day][hour] |= tmp;
	}

	public static void	printPadded(String str, int pad) {
		for (int i = 0; i < pad - str.length(); i++) {
			System.out.print(" ");
		}
		System.out.print(str);
	}

	public static void	print(int days[][], String names[]) {
		System.out.print("          ");
		for (int i = 0; i < DAYS; i++) {
			for (int j = 0; j < LESSONS; j++) {
				if (days[i][j] % 2 == 1) {
					System.out.print((j + 1) + ":00 " + WEEK_MAP[i % 7] + " ");
					if (i > 10) {
						System.out.print((i + 1) + "|");
					}
					else {
						System.out.print(" " + (i + 1) + "|");
					}
				}
			}
		}
		System.out.println();
		for (int i = 0; i < STUDENTS && names[i] != null; i++) {
			printPadded(names[i], 10);
			for (int j = 0; j < DAYS; j++) {
				for (int k = 0; k < LESSONS; k++) {
					if (days[j][k] % 2 == 1) {
						switch (days[j][k] >> (i * 2 + 1) & 3) {
							case 1:
								printPadded("1", 10);
								break ;
							case 2:
								printPadded("-1", 10);
								break ;
							default:
								printPadded("", 10);
						}
						System.out.print("|");
					}
				}
			}
			System.out.println();
		}
	}

	public static void	main(String[] args) {
		Scanner	kb = new Scanner(System.in);
		String	str = "";
		String	names[] = new String[STUDENTS];
		int		days[][] = new int[DAYS][LESSONS];
		char	tmp[];

		for (int i = 0; i < MAX_INPUT; i++) {
			str = kb.nextLine();
			if (str.equals(".")) {
				break ;
			}
			names[i] = str;
		}
		if (!str.equals(".")) {
			str = kb.nextLine();
		}
		for (int i = 0; i < MAX_INPUT; i++) {
			str = kb.next();
			if (str.equals(".")) {
				break ;
			}
			addLesson(days, str.toCharArray()[0], kb.nextLine());
		}
		kb.nextLine();
		while (true) {
			str = kb.next();
			if (str.equals(".")) {
				break ;
			}
			sign(days, findStudent(names, str), kb);
		}
		kb.nextLine();
		print(days, names);
	}
}

