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

	private void	mv(String what, String where) {
		File	wa;
		File	we;

		try {
			wa = new File(this.curDir + "/" + what);
			if (!wa.isFile()) {
				System.out.println("'" + what + "' is not a file");
				return ;
			}
			we = new File(this.curDir + "/" + where);
			if (where.contains("/")) {
				if (!we.isDirectory()) {
					System.out.println("'" + where + "' is not a directory");
					return ;
				}
				we = new File(this.curDir + "/" + where + "/" + what);
				if (!wa.renameTo(we)) {
					System.out.println("Cannot move '" + what + "'into '" + where + "'");
				}
				return ;
			}
			if (!we.isFile()) {
				System.out.println("'" + where + "' is not a file");
				return ;
			}
			if (!wa.renameTo(we)) {
				we.delete();
				if (!wa.renameTo(we)) {
					System.out.println("Cannot rename '" + what + "' into '" + where + "'");
					return ;
				}
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
				return ;
			}
			path = tmp.getAbsolutePath();
			while (path.contains("/./")) {
				path = path.replaceAll("/\\./", "/");
			}
			for (int i = 0; i < path.length(); i++) {
				path = path.replaceFirst("/[^/]+/[.]{2}", "");
			}
			if (path.endsWith("/.")) {
				path = path.replace("/.", "");
			}
			if (path.equals("") || path.startsWith("/.")) {
				path = "/";
			}
			this.curDir = path;
			System.out.println(this.curDir);
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
			else if (args[0].equals("mv")) {
				if (args.length != 3) {
					System.out.println("'mv' accepts only 2 arguments");
					continue ;
				}
				else {
					mv(args[1], args[2]);
				}
			}
			else {
				System.out.println("'" + args[0] + "' command unknown");
			}
		}
	}
}

