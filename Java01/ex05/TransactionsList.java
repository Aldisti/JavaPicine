import java.util.UUID;

interface TransactionsList {
	public void				add(Transaction t);
	public Transaction		remove(UUID id);
	public Transaction[]	toArray();
}
