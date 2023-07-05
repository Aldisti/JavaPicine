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

	public	Transaction(User r, User s, Category tc, int a) {
		this.identifier = UUID.randomUUID();
		this.recipient = r;
		this.sender = s;
		this.category = tc;
		if (a < 0 && tc == Category.CREDIT || a > 0 && tc == Category.DEBIT) {
			System.err.println("Invalid amount or transfer category");
			a = 0;
		}
		this.amount = a;
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
