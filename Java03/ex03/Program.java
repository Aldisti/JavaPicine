import java.util.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

class DownloadThread extends Thread {
	private Vector<String>	urls;
	private int				id;
	private static int		size = 0;
	private static Object	lock = new Object();

	public	DownloadThread(Vector<String> urls, int index) {
		this.urls = urls;
		this.id = index + 1;
	}

	public void	run() {
		String	tmp;
		int		n;

		while (this.urls.size() > 0) {
			synchronized (lock) {
				tmp = urls.get(0);
				urls.remove(0);
				n = ++size;
			}
			System.out.println("Thread-" + this.id + " start download file number " + n);
			try {
				InputStream in = new URL(tmp).openStream();
				Files.copy(in, Paths.get(tmp.substring(tmp.lastIndexOf('/') + 1, tmp.length())));
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				return ;
			}
			System.out.println("Thread-" + this.id + " finish download file number " + n);
		}
	}
}

class Program {

	private static String	URLS_FILES = "files_urls.txt";

	public static Vector<String>	getUrls(String path) {
		String			line;
		FileReader		file;
		BufferedReader	buf;
		Vector<String>	lines = new Vector<String>();

		try {
			file = new FileReader(path);
			buf = new BufferedReader(file);
			while ((line = buf.readLine()) != null) {
				lines.add(line.split(" ")[1]);
			}
			return (lines);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return (null);
		}
	}

	public static void	main(String[] args) {
		Vector<String>			urls;
		Vector<DownloadThread>	threads;
		int						count;

		if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
			System.out.println("Invalid arguments");
			return ;
		}
		count = Integer.valueOf(args[0].substring(args[0].indexOf('=') + 1, args[0].length()));
		if ((urls = getUrls(URLS_FILES)) == null) {
			System.out.println("'" + URLS_FILES + "' is corrupted");
			return ;
		}
		threads = new Vector<DownloadThread>();
		for (int i = 0; i < count; i++) {
			threads.add(new DownloadThread(urls, i));
			threads.get(i).start();
		}
	}
}

