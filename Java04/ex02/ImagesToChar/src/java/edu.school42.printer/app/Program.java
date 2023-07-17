import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;
import java.util.*;
import java.io.*;

class Program {
	private static String	PATH = "target/resources/it.bmp";

	@Parameter(names = "--white", description = "The color white")
    private static String	white;

    @Parameter(names = "--black", description = "The color black")
    private static String	black;

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
		//AsciiArt	art;
		JCommander	jc;

		jc = new JCommander(main);
        jc.parse(args);
		System.out.println("white=" + white + "-black=" + black);
		if (!checkSignature(PATH)) {
			System.out.println("Invalid file");
			return ;
		}
		//art = new AsciiArt(PATH);
		//art.draw(chars);
	}
}

