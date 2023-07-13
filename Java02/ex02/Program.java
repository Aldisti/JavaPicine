import java.util.*;
import java.io.*;

class Program {

	public static void	main(String[] args) {
		Minishell	shell;

		if (args.length != 1 || !args[0].startsWith("--current-folder=")) {
			System.out.println("Usage: java Program --current-folder=<your_current_folder>");
			return ;
		}
		shell = new Minishell(args[0]);
		shell.loop();
	}
}
