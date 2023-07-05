

class Program {
	public static void	main(String[] args) {
		User		u1 = new User("Alessandro", 150);
		User		u2 = new User("Marco", 850);
		Transaction	t1 = new Transaction(u1, u2, Category.CREDIT, 200);

		System.out.println(u1);
		System.out.println(u2);
		System.out.println(t1);
	}
}
