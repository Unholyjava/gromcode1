package lessons_8;

import org.hibernate.HibernateException;

public class ControllerUser {
	
	protected UserDAO userDao;
	
	public ControllerUser(UserDAO userDao) {
		super();
		this.userDao = userDao;
	}

	public User registerUser(User user) throws HibernateException {
		try {
			User userRegistered = userDao.findByNamePassword(user);
			if (userRegistered == null) {
				throw new Exception();
			}
			return userRegistered;
		} catch (Exception e){
			System.out.println("not found register User with name = " + user.getUserName()
				+ " from registerUser-method");
			e.printStackTrace();
		}
		return null;
	}
	
	public void login(String userName, String password) throws HibernateException {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		try {
			if (userDao.findByNamePassword(user) == null) {
				userDao.save(user);
			} else {
				System.out.println("Пользователь с таким именем и паролем зарегистрирован");
			}
		} catch (Exception e) {
			System.out.println("not login User with name = " + userName
				+ ", password = " + password + " from login-method");
			e.printStackTrace();
		}
	}
	
	public void logout() {
		
		
	}
}
