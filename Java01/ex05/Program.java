
class Program {
	public static void	main(String[] args) {
		Menu	menu;

		if (args.length > 0) {
			menu = new Menu(args[0]);
		}
		else {
			menu = new Menu("");
		}
		menu.loop();
	}
}
