
class Program {
	public static void	main(String[] args) {
		UsersArrayList	list = new UsersArrayList();

		list.addUser(new User("Alessandro", 150));
		list.addUser(new User("Giovanni", 250));
		list.addUser(new User("Marco", 350));
		list.addUser(new User("Alessio", 450));

		System.out.println(list.searchId(2));
		System.out.println(list.searchIndex(2));
		System.out.println();
		System.out.println(list.getSize());
	}
}
