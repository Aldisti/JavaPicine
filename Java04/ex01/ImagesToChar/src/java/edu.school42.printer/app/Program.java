
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
		String		path = "target/resources/it.bmp";

		if (args.length != 2) {
			System.out.println("Invalid arguments");
			return ;
		}
		if (!checkSignature(path)) {
			System.out.println("Invalid file");
			return ;
		}
		chars = new char[2];
		chars[0] = args[0].charAt(0);
		chars[1] = args[1].charAt(0);
		art = new AsciiArt(path);
		art.draw(chars);
	}
}

