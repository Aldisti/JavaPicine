class Print extends Thread {

	private String	line;
	private int		times;

	public	Print(String line, int times) {
		this.line = line;
		this.times = times;
	}

	public void run() {
		for (int i = 0; i < this.times; i++) {
			System.out.println(this.line);
		}
	}
}
