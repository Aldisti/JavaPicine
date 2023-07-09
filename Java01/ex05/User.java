
class User {
	private final int				id;
	private String					name;
	private int						balance;
	private TransactionsLinkedList	transactions;

	public	User(String name, int balance) {
		if (balance < 0) {
			throw new NegativeBalanceException("Balance cannot be negative");
		}
		this.id = UserIdsGenerator.getInstance().generateId();
		this.name = name;
		this.balance = balance;
		this.transactions = new TransactionsLinkedList();
	}

	public int		getId() {
		return (this.id);
	}

	public String	getName() {
		return (this.name);
	}

	public int		getBalance() {
		return (this.balance);
	}

	public void		setName(String name) {
		this.name = name;
	}

	public void		setBalance(int balance) {
		this.balance = balance;
	}

	public TransactionsLinkedList	getTransactions() {
		return (this.transactions);
	}

	@Override
	public String	toString() {
		return ("id: [" + this.id + "] - name: [" + this.name + "] - balance: [" + this.balance + "]");
	}
}
