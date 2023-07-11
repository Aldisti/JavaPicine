import java.util.UUID;

class TransactionsLinkedList implements TransactionsList {
	private TransactionNode	head;
	private int				size;

	public	TransactionsLinkedList() {
		head = null;
		size = 0;
	}

	public void				add(Transaction t) {
		TransactionNode	tmp = new TransactionNode(t);

		if (this.head != null) {
			tmp.setNext(this.head);
			this.head.setBack(tmp);
		}
		this.head = tmp;
		this.size++;
	}

	public Transaction		remove(UUID id) {
		TransactionNode	tmp = this.head;

		while (tmp != null) {
			if (tmp.getData().getIdentifier().toString().equals(id.toString())) {
				if (tmp.getNext() != null) {
					tmp.getNext().setBack(tmp.getBack());
				}
				if (tmp.getBack() != null) {
					tmp.getBack().setNext(tmp.getNext());
				}
				if (this.head == tmp) {
					this.head = this.head.getNext();
				}
				this.size--;
				return (tmp.getData());
			}
			tmp = tmp.getNext();
		}
		throw new TransactionNotFoundException("Transaction with id = "
				+ id.toString() + " not found");
	}

	public boolean			isIn(UUID id) {
		TransactionNode	tmp = this.head;

		while (tmp != null) {
			if (tmp.getData().getIdentifier() == id) {
				return (true);
			}
			tmp = tmp.getNext();
		}
		return (false);
	}

	public Transaction[]	toArray() {
		Transaction		tmp[] = new Transaction[this.size];
		TransactionNode	node = this.head;

		for (int i = 0; i < size; i++) {
			tmp[i] = node.getData();
			node = node.getNext();
		}
		return (tmp);
	}

	public void				print() {
		TransactionNode	tmp = this.head;

		while (tmp != null) {
			System.out.println(tmp.getData());
			tmp = tmp.getNext();
		}
	}
}

