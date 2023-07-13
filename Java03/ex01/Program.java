
class Program {
	public static void	main(String[] args) {
		Print	t1;
		Print	t2;
		int		n;

		if (args.length != 1 || !args[0].startsWith("--count=")) {
			return ;
		}
		n = Integer.valueOf(args[0].substring(args[0].indexOf('=') + 1, args[0].length()));
		t1 = new Print("Egg", n);
		t2 = new Print("Hen", n);
		t2.start();
		t1.start();
		try {
			t1.join();
			t2.join();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (int i = 0; i < n; i++) {
			System.out.println("Human");
		}
	}
}

