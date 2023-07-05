
class Program {
	public static void	main(String[] args) {
		User p1 = new User("Mike", 2600);
        User p2 = new User("John", 1000);
        User p3 = new User("Nikol", -200);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        System.out.println("\n||Transactions||");
        Transaction t1 = new Transaction(p1, p2, Transaction.Category.CREDIT, 1000);
        System.out.println(t1);
        Transaction t2 = new Transaction(p1, p2, Transaction.Category.DEBIT, -500);
        System.out.println(t2);
        Transaction t3 = new Transaction(p2, p3, Transaction.Category.CREDIT, 200);
        System.out.println(t3);
        Transaction t4 = new Transaction(p2, p3, Transaction.Category.CREDIT, 400);
        System.out.println(t4);

        TransactionsLinkedList tl = new TransactionsLinkedList();
        tl.add(t1);
        tl.add(t2);
        tl.add(t3);
        tl.add(t4);
        System.out.println("\nRemoving Transaction ID: " + t2.getIdentifier());
        tl.remove(t2.getIdentifier());

        System.out.println("\n||Transaction print||");
        tl.print();
	}
}
