

class User {
	private int		id;
	private String	name;
	private int		balance;

	public void	User() {
		id = 0;
		balance = 0;
		name = "DEFAULT";
	}

	public void	User(int id, String name, int balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
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
}
