
class Program {
	public static void	main(String[] args) {
		User		u1 = new User("Alessandro", 150);

		for (int i = 0; i < 100; i++) {
			u1 = new User("Alessandro", 150);
		}
		System.out.println(u1);
	}
}
