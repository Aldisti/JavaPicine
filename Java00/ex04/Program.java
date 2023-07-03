import java.util.Scanner;

class Program {

	private static final short	MAX_VALUE = 10;
	private static final short	MIN_VALUE = 0;

	public static void	merge(short tmp[][], short dict[][], int start, int end)
	{
		if (end - start == 0)
			return ;

		int	ls = start, re = end;
		int	le = (start + end) / 2;
		int	rs = (start + end) / 2 + 1;
		int	i;

		merge(tmp, dict, ls, le);
		merge(tmp, dict, rs, re);
	
		i = 0;
		while (ls <= le && rs <= re) {
			if (dict[ls][1] == dict[rs][1]) {
				tmp[i++] = (dict[ls][0] < dict[rs][0]) ? dict[ls++] : dict[rs++];
			}
			else {
				tmp[i++] = (dict[ls][1] > dict[rs][1]) ? dict[ls++] : dict[rs++];
			}
		}
		while (ls <= le) {
			tmp[i++] = dict[ls++];
		}
		while (rs <= re) {
			tmp[i++] = dict[rs++];
		}
		i = 0;
		while (start <= end) {
			dict[start++] = tmp[i++];
		}
	}

	public static int	map(int x, int in_min, int in_max, int out_min, int out_max) {
		return ((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
	}

	public static int	addToArray(char c, short dict[][], int size)
	{
		int	i = 0;

		for (i = 0; i < size; i++) {
			if (dict[i][0] == (short)c) {
				dict[i][1] += 1;
				return (size);
			}
		}
		if (i == size) {
			dict[i][0] = (short)c;
			dict[i][1] = 1;
			size++;
		}
		return (size);
	}

	public static void	main(String[] args) {
		Scanner	kb = new Scanner(System.in);
		String	str;

		System.out.println("->");
		str = kb.nextLine();

		int		len = str.length();
		short	dict[][] = new short[len][2];
		char	line[] = str.toCharArray();
		short	tmp[][] = new short[len][2];
		int		size = 0;

		for (char i:line) {
			size = addToArray(i, dict, size);
		}
		System.out.println("After merge ----------");
		merge(tmp, dict, 0, size - 1);
		len = dict[0][1];
		//for (int i = 0; i < size && i < 10; i++) {
		//	dict[i][1] = (short)map(dict[i][1], (short)0, (short)len, MIN_VALUE, MAX_VALUE);
		//}

		for (int i = 0; i < size && i < 10; i++) {
			System.out.println((char)dict[i][0] + ": " + dict[i][1]);
		}

		short	n;
		for (int i = MAX_VALUE + 2; i >= 0; i--) {
			for (int j = 0; j < 10 && j < size; j++) {
				n = (short)map(dict[j][1], (short)0, dict[0][1], MIN_VALUE, MAX_VALUE);
				if (i == n + 1) {
					if (dict[j][1] < 10) {
						System.out.print("  ");
					}
					else if (dict[j][1] < 100) {
						System.out.print(" ");
					}
					System.out.print(dict[j][1]);
				}
				else if (i <= n && i > 0) {
					System.out.print("  #");
				}
				else if (i == 0) {
					System.out.print("  " + (char)dict[j][0]);
				}
			}
			System.out.println();
		}
	}
}

