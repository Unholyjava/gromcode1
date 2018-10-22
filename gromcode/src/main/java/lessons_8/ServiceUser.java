package lessons_8;

import org.hibernate.HibernateException;

public class ServiceUser {
	
	private UserDAO userDao;
	private SessionUser sessionUser;
	
	public ServiceUser(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public User registerUserService(User user) throws HibernateException {
		try {
			if (userDao.findByNamePassword(user.getUserName(), user.getPassword()) == null) {
				userDao.save(user);
				return user;
			}
			throw new Exception ("Пользователь с таким именем и паролем зарегистрирован");
		} catch (Exception e) {
			System.out.println("not register User with name = " + user.getUserName()
				+ ", password = " + user.getPassword() + " from registerUser-method");
			e.printStackTrace();
		}
		return null;
	}
	
	public void loginService(String userName, String password) throws HibernateException {
		try {
			if (userDao.findByNamePassword(userName, password) != null) {
				sessionUser = new SessionUser(userName, password); 
			} else {
				throw new Exception ("Пользователь с таким именем и паролем не зарегистрирован");
			}
		} catch (Exception e) {
			System.out.println("not login User with name = " + userName
				+ ", password = " + password + " from login-method");
			e.printStackTrace();
		}
	}
	
	public void logoutService() {
		sessionUser.deleteUserSession();
	}

	public SessionUser getSessionUser() {
		return sessionUser;
	}
}
