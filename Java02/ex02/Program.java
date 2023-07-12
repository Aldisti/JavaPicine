import java.util.*;
import java.io.*;

class Minishell {
	private File	cur;
	private String	curDir;

	public	Minishell(String path) {
		this.curDir = path.substring(path.indexOf('=') + 1);
		System.out.println(this.curDir);
	}

	private void		ls() {
		File	dir;
		File[]	cont;

		try {
			dir = new File(this.curDir);
			if (!dir.exists()) {
				System.out.println("Cannot execute ls");
				return ;
			}
			cont = dir.listFiles();
			for (File file : cont) {
				System.out.printf("%s	%.1f KB\n", file.getName(), (double)file.length() / 1000);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void	cd(String path) {
		File		tmp;
		String[]	arr;

		if (path.charAt(0) != '/') {
			path = this.curDir + "/" + path;
		}
		try {
			tmp = new File(path);
			if (!tmp.exists() || !tmp.isDirectory()) {
				System.out.println("cd cannot access '" + path + "'");
			}
			else {
				path = tmp.getAbsolutePath();
				while (path.contains("/./")) {
					path = path.replaceAll("/\\./", "/");
				}
				System.out.println(path);
				for (int i = 0; i < path.length(); i++) {
					path = path.replaceFirst("/[^/]+/[.]{2}", "");
				}
				if (path.endsWith("/.")) {
					path.replace("/.", "");
				}
				if (path.equals("") || path.startsWith("/.")) {
					path = "/";
				}
				this.curDir = path;
				System.out.println(this.curDir);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ;
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
				if (args.length > 1) {
					System.out.println("'ls' doesn't accept any argument");
					continue ;
				}
				ls();
			}
			else if (args[0].equals("cd")) {
				if (cmd.indexOf(' ') == -1 || args.length != 2) {
					System.out.println("'cd' accepts only 1 argument");
					continue ;
				}
				else {
					cd(args[1]);
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
