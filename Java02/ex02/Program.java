import java.util.*;
import java.io.*;

class Minishell {
	private File	cur;
	private String	curDir;

	public	Minishell(String path) {
		this.curDir = path.substring(path.indexOf('=') + 1);
		System.out.println(this.curDir);
	}

	public void		ls(String[] args) {
		File	dir;
		File[]	cont;

		for (String path : args) {
			if (path == null || path.length() <= 0) {
				continue ;
			}
			try {
				if (path.startsWith("/")) {
					dir = new File(path);
				}
				else {
					dir = new File(this.curDir + "/" + path);
				}
				if (!dir.exists()) {
					System.out.println("\'" + path + "' not found");
					continue ;
				}
				System.out.println(path + ":");
				cont = dir.listFiles();
				for (File file : cont) {
					System.out.printf("%s	%.1f KB\n", file.getName(), (double)file.length() / 1000);
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (args.length > 1) {
				System.out.println();
			}
		}
	}

	public void	loop() {
		Scanner		kb = new Scanner(System.in);
		String		cmd;
		String[]	args;

		while (true) {
			System.out.print("->");
			cmd = kb.nextLine().trim();
			if (cmd == null || cmd.equals("exit")) {
				break ;
			}
			args = cmd.split("\\s+");
			if (args[0].equals("ls")) {
				if (cmd.indexOf(' ') == -1) {
					ls(new String("").split(" "));
				}
				else {
					ls(cmd.substring(cmd.indexOf(' ')).split("\\s+"));
				}
			}
		}
	}
}

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
