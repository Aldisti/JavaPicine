import java.util.UUID;

class TransactionService {
	private UsersList				users;

	public	TransactionService() {
		this.users = new UsersArrayList();
	}

	public void				addUser(User u) {
		users.addUser(u);
	}

	public int				getBalance(int id) {
		return (users.searchId(id).getBalance());
	}

	public void				printBalance(int id) {
		System.out.println(users.searchId(id).getName()
				+ " - " + users.searchId(id).getBalance());
	}

	public void				doTransaction(int id1, int id2, int amount) {
		if (id1 == id2) {
			throw new IllegalTransactionException("Illegal transaction same user");
		}
		User		u1 = this.users.searchId(id1);
		User		u2 = this.users.searchId(id2);
		Transaction	tmp = new Transaction(u1, u2, amount);

		tmp.doTransaction();
		u1.getTransactions().add(tmp);
		u2.getTransactions().add(tmp);
	}

	public Transaction[]	getTransactionsById(int id) {
		return (this.users.searchId(id).getTransactions().toArray());
	}

	public void				removeById(UUID transactionId, int userId) {
		Transaction	tmp;

		tmp = this.users.searchId(userId).getTransactions().remove(transactionId);
		System.out.print("Transfer to " + tmp.getRecipient().getName());
		System.out.print("(id = " + tmp.getRecipient().getId() + ") ");
		System.out.println(tmp.getAmount() + " removed");
	}

	public Transaction[]	checkTransactions() {
		TransactionsLinkedList	tmp = new TransactionsLinkedList();
		Transaction[]	array;

		for (int j = 0; j < this.users.getSize(); j++) {
			array = this.users.searchId(j + 1).getTransactions().toArray();
			for (int i = 0; i < array.length; i++) {
				if (!array[i].getRecipient().getTransactions().isIn(array[i].getIdentifier())
					|| !array[i].getSender().getTransactions().isIn(array[i].getIdentifier()))
					tmp.add(array[i]);
			}
		}
		return (tmp.toArray());
	}
}
