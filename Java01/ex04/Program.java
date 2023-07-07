
class Program {
	public static void	main(String[] args) {
		User p1 = new User("Mike", 2600);
        User p2 = new User("John", 1000);
        User p3 = new User("Nikol", 200);
		TransactionService	service = new TransactionService();

		System.out.println("Before the transactions:");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);

		service.addUser(p1);
		service.addUser(p2);
		service.addUser(p3);

		System.out.println("---------------------------------");

		service.doTransaction(p1.getId(), p3.getId(), -1000);
		service.doTransaction(p1.getId(), p2.getId(), 300);
		service.doTransaction(p3.getId(), p1.getId(), 1050);

		System.out.println("After the transactions:");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);

		System.out.println("User 1 ----------");
		Transaction[]	tmp = p1.getTransactions().toArray();
		for (int i = 0; i < tmp.length; i++) {
			System.out.println(tmp[i]);
			System.out.println();
		}
		System.out.println("User 2 ----------");
		tmp = p2.getTransactions().toArray();
		for (int i = 0; i < tmp.length; i++) {
			System.out.println(tmp[i]);
			System.out.println();
		}
		System.out.println("User 3 ----------");
		tmp = p3.getTransactions().toArray();
		for (int i = 0; i < tmp.length; i++) {
			System.out.println(tmp[i]);
			System.out.println();
		}

		service.removeById(p3.getTransactions().toArray()[0].getIdentifier(), p3.getId());
		service.removeById(p2.getTransactions().toArray()[0].getIdentifier(), p2.getId());

		System.out.println("User 1 ----------");
		tmp = p1.getTransactions().toArray();
		for (int i = 0; i < tmp.length; i++) {
			System.out.println(tmp[i]);
			System.out.println();
		}
		System.out.println("User 2 ----------");
		tmp = p2.getTransactions().toArray();
		for (int i = 0; i < tmp.length; i++) {
			System.out.println(tmp[i]);
			System.out.println();
		}
		System.out.println("User 3 ----------");
		tmp = p3.getTransactions().toArray();
		for (int i = 0; i < tmp.length; i++) {
			System.out.println(tmp[i]);
			System.out.println();
		}

		System.out.println("---------------------------------");
		System.out.println("Unpaired transactions: ");
		tmp = service.checkTransactions(p1.getTransactions().toArray());
		for (int i = 0; i < tmp.length; i++) {
			System.out.println(tmp[i]);
			System.out.println();
		}
	}
}
