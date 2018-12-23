package lessons_8;

public class ServiceUser {
	
	private UserDAO userDao;
	private SessionUser sessionUser;
	
	public ServiceUser(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public User registerUserService(User user) throws Exception {
		if (userDao.findByNamePassword(user.getUserName(), user.getPassword()) == null) {
			userDao.save(user);
			return user;
		}
			throw new Exception ("Пользователь с таким именем и паролем зарегистрирован\n"
				+ "not register User with name = " + user.getUserName()
				+ ", password = " + user.getPassword() + " from registerUser-method");
		}
	
	public void loginService(String userName, String password) throws Exception {
		if (userDao.findByNamePassword(userName, password) != null) {
			sessionUser = new SessionUser(userName, password); 
		} else {
			throw new Exception ("Пользователь с таким именем и паролем не зарегистрирован\n"
				+ "not login User with name = " + userName
				+ ", password = " + password + " from login-method");
		}
	}
	
	public void logoutService() {
		sessionUser.deleteUserSession();
	}

	public SessionUser getSessionUser() {
		return sessionUser;
	}
}
