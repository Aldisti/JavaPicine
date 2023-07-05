
class UsersArrayList implements UsersList {
	private User	users[];
	private int		size;
	private int		maxSize = 10;

	public UsersArrayList() {
		this.users = new User[maxSize];
		this.size = 0;
	}

	public void	addUser(User u) {
		this.users[this.size++] = u;
		if (size != this.maxSize) {
			return ;
		}
		this.maxSize += this.maxSize / 2;
		User	tmp[] = new User[this.maxSize];
		for (int i = 0; i < this.size; i++) {
			tmp[i] = this.users[i];
		}
		this.users = tmp;
	}

	public User	searchId(int id) {
		for (int i = 0; i < this.size; i++) {
			if (this.users[i].getId() == id) {
				return (this.users[i]);
			}
		}
		throw new UserNotFoundException("User not found");
	}

	public User	searchIndex(int index) {
		if (index < 0 || index >= this.size) {
			throw new UserNotFoundException("User not found");
		}
		return (this.users[index]);
	}

	public int	getSize() {
		return (this.size);
	}
}
