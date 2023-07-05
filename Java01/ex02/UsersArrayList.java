
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
		if (size == this.maxSize) {
			// resize
		}
	}

	public int	getSize() {
		return (this.size);
	}



	public class UserNotFoundException extends Exception {
		public	UserNotFoundException(String errorMessage) {
			super(errorMessage);
		}
	}
}
