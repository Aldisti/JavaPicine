class TransactionNode {
	private TransactionNode	next;
	private TransactionNode	back;
	private Transaction		data;

	public TransactionNode(Transaction data) {
		this.data = data;
		this.next = null;
		this.back = null;
	}

	public void				setNext(TransactionNode next) {
		this.next = next;
	}

	public void				setBack(TransactionNode back) {
		this.back = back;
	}

	public TransactionNode	getNext() {
		return (this.next);
	}

	public TransactionNode	getBack() {
		return (this.back);
	}

	public Transaction		getData() {
		return (this.data);
	}
}
