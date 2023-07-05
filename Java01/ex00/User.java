

class User {
	private int		id;
	private String	name;
	private int		balance;

	public	User() {
		id = 0;
		balance = 0;
		name = "DEFAULT";
	}

	public	User(String name, int balance) {
		this.id = 20022004;
		this.name = name;
		this.balance = balance;
		if (balance < 0) {
			System.err.println("'balance' cannot be negative");
			this.balance = 0;
		}
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

	public void		setId(int id) {
		this.id = id;
	}

	public void		setName(String name) {
		this.name = name;
	}

	public void		setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String	toString() {
		return ("id: [" + this.id + "] - name: [" + this.name + "] - balance: [" + this.balance + "]");
	}
}
