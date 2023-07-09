import java.util.UUID;

class Transaction {

	static enum	Category {
		DEBIT,
		CREDIT
	}

	private UUID		identifier;
	private User		recipient;
	private User		sender;
	private Category	category;
	private int			amount;

	public	Transaction(User r, User s, int a) {
		if (a < 0) {
			this.category = category.DEBIT;
		}
		else {
			this.category = category.CREDIT;
		}
		this.identifier = UUID.randomUUID();
		this.recipient = r;
		this.sender = s;
		this.amount = a;
	}

	public void	doTransaction() {
		if (this.category == Category.DEBIT && this.recipient.getBalance() < (this.amount * -1)) {
			throw new IllegalTransactionException("Illegal transaction amount");
		}
		else if (this.category == Category.CREDIT && this.sender.getBalance() < this.amount) {
			throw new IllegalTransactionException("Illegal transaction amount");
		}
		this.recipient.setBalance(this.recipient.getBalance() + this.amount);
		this.sender.setBalance(this.sender.getBalance() - this.amount);
	}

	public UUID		getIdentifier() {
		return (this.identifier);
	}

	public User		getRecipient() {
		return (this.recipient);
	}

	public User		getSender() {
		return (this.sender);
	}

	public Category	getCategory() {
		return (this.category);
	}

	public int		getAmount() {
		return (this.amount);
	}

	@Override
	public String	toString() {
		return ("id: [" + this.identifier.toString() + "]\nrecipient: "
			+ this.recipient + "\nsender: " + this.sender
			+ "\namount: [" + this.amount + "]");
	}
}
