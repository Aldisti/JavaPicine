
class UserIdsGenerator {

	private static int				id;
	private static UserIdsGenerator	instance = null;

	private	UserIdsGenerator() {
		this.id = 1;
	}

	public static UserIdsGenerator	getInstance() {
		if (instance == null) {
			instance = new UserIdsGenerator();
		}
		return (instance);
	}

	public static int	generateId() {
		return (id++);
	}
}

