import java.util.*;
import java.io.*;
import java.net.URL;
import java.nio.file.*;

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
				Files.copy(in, Paths.get(tmp.substring(tmp.lastIndexOf('/') + 1, tmp.length())), StandardCopyOption.REPLACE_EXISTING);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				return ;
			}
			System.out.println("Thread-" + this.id + " finish download file number " + n);
		}
	}
}
