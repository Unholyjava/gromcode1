package lessons_8;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAO extends CommonDAO<User> implements DAO<User> {
	public UserDAO(Class<User> classCurrent) {
		super(classCurrent);
	}

	private static final String SELECT_USER_BY_NAME_AND_PASSWORD = 
				"SELECT * FROM USERS WHERE USER_NAME = :name AND PASSWORD = :password";
	
	public User findById(long id) throws Exception {
		return super.findById(id);
	}
	
	public User save(User user) throws Exception {
		return super.save(user);
	}
	
	public User delete(long id) throws Exception {
		return super.delete(id);
	}
	
	public User update (User user) throws Exception {
		return super.update(user);
	}
	
	public User findByNamePassword(String userName, String password) throws Exception {
		try (Session session = createSessionFactory().openSession();) {
			Query<User> query = session.createNativeQuery(SELECT_USER_BY_NAME_AND_PASSWORD, User.class);
			query.setParameter("name", userName);
			query.setParameter("password", password);
			if (query.getSingleResult() == null) {
				return null;
			}
			return query.getSingleResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception ("findByNamePassword of User, name = " + userName
				+ ", password = " + password + ", is failed");
		} catch (NoResultException e) {
			return null;
		}
	}
}

