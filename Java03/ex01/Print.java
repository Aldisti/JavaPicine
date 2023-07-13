
class Print extends Thread {

	private String			line;
	private int				times;
	private int				type;
	private static int		count = 0;
	private static Object	lock = new Object();

	public	Print(String line, int times) {
		this.line = line;
		this.times = times;
		this.type = 1;
		if (line.equals("Egg")) {
			this.type = 0;
		}
	}

	public void run() {
		while (this.times > 0) {
			synchronized(lock) {
				if (count % 2 == this.type) {
					System.out.println(this.line);
					this.times--;
					count++;
				}
			}
		}
	}
}

