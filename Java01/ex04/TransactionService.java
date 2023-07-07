import java.util.UUID;

class TransactionService {
	private UsersList	users;

	public	TransactionService() {
		this.users = new UsersArrayList();
	}

	public void				addUser(User u) {
		users.addUser(u);
	}

	public int				getBalanceId(int id) {
		try {
			return (users.searchId(id).getBalance());
		}
		catch (UserNotFoundException e) {
			System.err.println(e);
			return (0);
		}
	}

	public int				getBalanceIndex(int index) {
		try {
			return (users.searchIndex(index).getBalance());
		}
		catch (UserNotFoundException e) {
			System.err.println(e);
			return (0);
		}
	}

	public void				doTransaction(int id1, int id2, int amount) {
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
		this.users.searchId(userId).getTransactions().remove(transactionId);
	}

	public Transaction[]	checkTransactions(Transaction[] array) {
		TransactionsLinkedList	tmp = new TransactionsLinkedList();

		for (int i = 0; i < array.length; i++) {
			if (!array[i].getRecipient().getTransactions().isIn(array[i].getIdentifier())
				|| !array[i].getSender().getTransactions().isIn(array[i].getIdentifier()))
				tmp.add(array[i]);
		}
		return (tmp.toArray());
	}
}
