import java.util.Scanner;

class Program {

	private static final int	DAYS = 30;
	private static final int	LESSONS = 5;
	private static final int	STUDENTS = 10;
	private static final int	MAX_INPUT = 10;
	private static final int	DAYS_MAP[] = {1, -1, -1, -1, 2, 5, -1, 4, 6, -1, 3, -1, -1, -1, 0};

	public static int	getDayIndex(char letters[]) {
		return (DAYS_MAP[((((int)letters[0] * (int)letters[1]) % 1000) / 10) % 16]);
	}

	public static void	addLesson(int days[][], char c, String line) {
		int	hour = (int)c - '1';
		int	day = getDayIndex(line.toCharArray());

		
		//System.out.println("day: " + day + " hour: " + hour);
		for (; day < DAYS; day += 7) {
			//System.out.println("day: " + day + " hour: " + hour);
			days[day][hour] = 1;
			//System.out.println("day: " + days[day][hour]);
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
		boolean	presence = kb.nextLine().equals("HERE");
		int		tmp = 2;

		if (presence)
			tmp = 1;
		tmp = tmp << (index * 2 + 1);
		//System.out.println("tmp: " + tmp);
		//System.out.println("B day: " + days[day][hour]);
		days[day][hour] |= tmp;
		//System.out.println("A day: " + days[day][hour]);
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

		for (int i = 0; i < DAYS; i++) {
			System.out.println("Day: " + (i + 1));
			for (int j = 0; j < LESSONS; j++) {
				System.out.print("	" + (j + 1) + ": " + days[i][j]);
			}
			System.out.println();
		}
	}
}

