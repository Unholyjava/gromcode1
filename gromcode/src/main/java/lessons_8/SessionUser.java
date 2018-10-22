package lessons_8;

public class SessionUser {
	private User userSession;
	
	public SessionUser(String userName, String password) {
		User userSession = new User();
		userSession.setUserName(userName);
		userSession.setPassword(password);
		this.userSession = userSession;
	}
	
	public void deleteUserSession() {
		userSession = null;
	}

	public User getUserSession() {
		return userSession;
	}
}
