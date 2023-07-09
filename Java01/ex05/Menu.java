import java.util.Scanner;
import java.util.UUID;

class Menu {
	private TransactionService		service;
	private boolean					dev;
	private Scanner					kb;
	private boolean					run;

	public Menu(String mode) {
		this.kb = new Scanner(System.in);
		this.service = new TransactionService();
		this.dev = mode.equals("--profile=dev");
		this.run = false;
	}

	public void	loop() {
		this.run = (this.run == false);
		while (this.run) {
			this.showMenu();
			this.getInput();
		}
		System.out.println("Exit");
	}

	private void	showMenu() {
		System.out.println("---------------------------------------------------------");
		System.out.println("1. Add a user");
		System.out.println("2. View user balances");
		System.out.println("3. Perform a transfer");
		System.out.println("4. View all transactions for a specific user");
		if (this.dev) {
			System.out.println("5. DEV - remove a transfer by ID");
			System.out.println("6. DEV - check transfer validity");
			System.out.println("7. Finish execution");
		}
		else {
			System.out.println("5. Finish execution");
		}
		System.out.print("->");
	}

	private void	addUser() {
		String	name;
		int		balance;
		User	user;

		System.out.println("Enter a user name and a balance");
		System.out.print("->");
		try {
			name = this.kb.next();
			balance = this.kb.nextInt();
			this.kb.nextLine();
		}
		catch (Exception e) {
			this.kb.nextLine();
			System.out.println(e);
			return ;
		}
		try {
			user = new User(name, balance);
			this.service.addUser(user);
		}
		catch (Exception e) {
			System.out.println(e);
			return ;
		}
		System.out.println("User with id = " + user.getId() + " is added");
	}

	private void	viewBalance() {
		int		id;

		System.out.println("Enter a user ID");
		System.out.print("->");
		try {
			id = this.kb.nextInt();
			this.kb.nextLine();
		}
		catch (Exception e) {
			this.kb.nextLine();
			System.out.println(e);
			return ;
		}
		try {
			this.service.printBalance(id);
		}
		catch (Exception e) {
			System.out.println(e);
			return ;
		}
	}

	private void	performTransfer() {
		int	sender;
		int	recipient;
		int	amount;

		System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
		System.out.print("->");
		try {
			sender = this.kb.nextInt();
		}
		catch (Exception e) {
			this.kb.nextLine();
			System.out.println("Sender ID must be a number");
			return ;
		}
		try {
			recipient = this.kb.nextInt();
		}
		catch (Exception e) {
			this.kb.nextLine();
			System.out.println("Recipient ID must be a number");
			return ;
		}
		try {
			amount = this.kb.nextInt();
			this.kb.nextLine();
		}
		catch (Exception e) {
			this.kb.nextLine();
			System.out.println("Transfer amount must be a number");
			return ;
		}
		try {
			this.service.doTransaction(recipient, sender, amount);
		}
		catch (Exception e) {
			System.out.println(e);
			return ;
		}
		System.out.println("The transfer is completed");
	}

	private void	viewTransactions() {
		int				id;
		Transaction[]	array;
		User			tmp;

		System.out.println("Enter a user ID");
		System.out.print("->");
		try {
			id = this.kb.nextInt();
			this.kb.nextLine();
		}
		catch (Exception e) {
			this.kb.nextLine();
			System.out.println("User ID must be a number");
			return ;
		}
		try {
			array = this.service.getTransactionsById(id);
		}
		catch (Exception e) {
			System.out.println(e);
			return ;
		}
		for (int i = 0; i < array.length; i++) {
			tmp = array[i].getRecipient();
			System.out.print("To " + tmp.getName());
			System.out.print("(id = " + tmp.getId() + ") ");
			System.out.print(array[i].getAmount());
			System.out.print(" with id = " + array[i].getIdentifier().toString());
			System.out.println();
//			if (array[i].getRecipient().getId() == id) {
//				System.out.print(array[i].getSender().getName());
//			}
		}
	}

	private void	removeTransfer() {
		UUID	transferId;
		int		userId;
		String	tmp;

		System.out.println("Enter a user ID and a transfer ID");
		System.out.print("->");
		try {
			userId = this.kb.nextInt();
		}
		catch (Exception e) {
			this.kb.nextLine();
			System.out.println("User ID must be a number");
			return ;
		}
		tmp = this.kb.next();
		try {
			transferId = UUID.fromString(tmp);
			this.service.removeById(transferId, userId);
		}
		catch (Exception e) {
			System.out.println(e);
			return ;
		}
	}

	private void	checkTransfers() {
		Transaction[]	tmp;

		try {
			tmp = this.service.checkTransactions();
		}
		catch (Exception e) {
			System.out.println(e);
			return ;
		}
		if (tmp.length == 0) {
			System.out.println("All the transfers are valid");
			return ;
		}
		System.out.println("Check results:");
		for (int i = 0; i < tmp.length; i++) {
			System.out.print(tmp[i].getRecipient().getName());
			System.out.print("(id = " + tmp[i].getRecipient().getId() + ")");
			System.out.print(" has an unacknowledged transfer id = ");
			System.out.print(tmp[i].getIdentifier().toString());
			System.out.print(" from " + tmp[i].getSender().getName());
			System.out.print("(id = " + tmp[i].getSender().getId() + ")");
			System.out.println(" for " + tmp[i].getAmount());
		}
	}

	private void	getInput() {
		int	n;

		try {
			n = this.kb.nextInt();
			this.kb.nextLine();
		}
		catch (Exception e) {
			this.kb.nextLine();
			System.out.println("You must insert a number");
			return ;
		}
		switch (n) {
			case 1:
				this.addUser();
				break ;
			case 2:
				this.viewBalance();
				break ;
			case 3:
				this.performTransfer();
				break ;
			case 4:
				this.viewTransactions();
				break ;
			case 5:
				if (!this.dev) {
					this.run = false;
					return ;
				}
				else {
					this.removeTransfer();
					break ;
				}
			case 6:
				if (this.dev) {
					this.checkTransfers();
					break ;
				}
			case 7:
				if (this.dev) {
					this.run = false;
					return ;
				}
			default:
				System.out.println("You must insert a valid number");
		}
	}
}

