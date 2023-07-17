package edu.school42.printer.app;

import edu.school42.printer.logic.AsciiArt;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import java.util.*;
import java.io.*;

@Parameters(separators="=")
class Program {
	private static String	PATH = "target/resources/it.bmp";

	@Parameter(names = "--white", description = "The color white", required=true, validateWith=ColorValidator.class)
    private static String	white;

    @Parameter(names = "--black", description = "The color black", required=true, validateWith=ColorValidator.class)
    private static String	black;

	public static int	toHex(String line) {
		char[]	str = line.toCharArray();

		return (((str[0] <= 58) ? str[0] - '0' : str[0] - 'A' + 10) * 16
				+ ((str[1] <= 58) ? str[1] - '0' : str[1] - 'A' + 10));
	}

	public static class ColorValidator implements IParameterValidator {
		public void validate(String name, String value) throws ParameterException {
			if (!value.equals("RED") &&  !value.equals("BLUE") && !value.equals("ORANGE")
				&& !value.equals("BLACK") && !value.equals("WHITE") && !value.equals("YELLOW")
				&& !value.equals("GREEN")) {
				throw new ParameterException("Parameter " + name + " should be a valid color");
			}
		}
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
		Program	tmp = new Program();
		JCommander	jc;

		jc = new JCommander(tmp);
		try {
			jc.parse(args);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return ;
		}
		if (!checkSignature(PATH)) {
			System.out.println("Invalid file");
			return ;
		}
		art = new AsciiArt(PATH);
		art.draw(black, white);
	}
}

