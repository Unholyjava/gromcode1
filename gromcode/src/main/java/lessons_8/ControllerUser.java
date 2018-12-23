package lessons_8;

public class ControllerUser {
	
	private ServiceUser serviceUser;
	
	public ControllerUser(ServiceUser serviceUser) {
		this.serviceUser = serviceUser;
	}

	public User registerUser(User user) throws Exception {
		return serviceUser.registerUserService(user);
	}
	
	public void login(String userName, String password) throws Exception {
		serviceUser.loginService(userName, password);
	}
	
	public void logout() {
		serviceUser.logoutService();
	}
}
