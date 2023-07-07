import java.util.UUID;

interface TransactionsList {
	public void				add(Transaction t);
	public void				remove(UUID id);
	public Transaction[]	toArray();
}
