import java.util.*;
import java.io.*;

public class Program {

	private static int					MAX_CHAR = 0;
	private static Map<int[], String>	csv;

	public static int	toHex(String line) {
		char[]	str = line.toCharArray();
		int		n = 0;

		n += ((str[0] <= 58) ? str[0] - '0' : str[0] - 'A' + 10) * 16;
		n += ((str[1] <= 58) ? str[1] - '0' : str[1] - 'A' + 10);
		return (n);
	}

	private static void	getCsv(String path) {
		FileInputStream		file;
		List<Integer>		key = new ArrayList<Integer>();
		String				value = "";
		String				tmp = "";
		int[]				arr;
		int					i = -1;
		boolean				stat = false;

		try {
			csv = new HashMap<>();
			file = new FileInputStream(path);
			while ((i = file.read()) != -1) {
				if (i == 10) {
					stat = false;
					MAX_CHAR = (key.size() > MAX_CHAR) ? key.size() : MAX_CHAR;
					arr = new int[key.size()];
					for (int j = 0; j < key.size(); j++) {
						arr[j] = key.get(j);
					}
					csv.put(arr, value);
					key.clear();
					value = "";
					continue ;
				}
				if ((char)i == ',') {
					stat = true;
				}
				else if (!stat) {
					value += (char)i;
				}
				else if (stat && i != 32) {
					tmp += (char)i;
					if (tmp.length() == 2) {
						key.add(toHex(tmp));
						tmp = "";
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
	}

	private static String	getFromCsv(int[] num) {
		boolean	tmp;

		for (int[] key : csv.keySet()) {
			tmp = true;
			for (int i = 0; i < key.length && i < num.length; i++) {
				if (num[i] != key[i]) {
					tmp = false;
					break ;
				}
			}
			if (tmp) {
				return (csv.get(key));
			}
		}
		return (null);
	}

	private static int[]	getStart(String path) {
		FileInputStream		file;
		int[]				tmp = new int[MAX_CHAR];
		int					b = -1;

		try {
			file = new FileInputStream(path);
			for (int i = 0; i < MAX_CHAR && (b = file.read()) != -1; i++) {
				tmp[i] = b;
			}
			return (tmp);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return (null);
	}

	public static void main(String args[]){
		Scanner				kb = new Scanner(System.in);
		FileWriter			file;
		String				str;
		int[]				tmp;

		getCsv("test");
		try {
			file = new FileWriter("result.txt");
			while (!(str = kb.nextLine()).equals("42")) {
				tmp = getStart(str);
				str = getFromCsv(tmp);
				if (str != null) {
					System.out.println("PROCESSED: " + str);
					file.write(str + "\n");
				}
				else {
					System.out.println("UNDEFINED");
				}
			}
			file.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
