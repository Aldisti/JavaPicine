
import java.util.*;
import java.io.*;

class Program {
	private static int[]	SIGNATURE = {66, 77};

	public static int	toHex(String line) {
		char[]	str = line.toCharArray();

		return (((str[0] <= 58) ? str[0] - '0' : str[0] - 'A' + 10) * 16
				+ ((str[1] <= 58) ? str[1] - '0' : str[1] - 'A' + 10));
	}

	private static boolean	checkSignature(String path) {
		FileInputStream		file;

		try {
			file = new FileInputStream(path);
			if (file.read() != 66 || file.read() != 77) {
				return (false);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return (false);
		}
		return (true);
	}

	public static void	main(String[] args) throws IOException {
		AsciiArt	art;
		char[]		chars;
		String		path;

		if (args.length != 2) {
			System.out.println("Invalid arguments");
			return ;
		}
		chars = args[0].toCharArray();
		path = args[1];
		if (!checkSignature(path)) {
			System.out.println("Invalid file");
			return ;
		}
		art = new AsciiArt(path);
		art.draw(chars);
	}
}

