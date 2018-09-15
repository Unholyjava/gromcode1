package lessons_8;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAO extends CommonDAO<User> implements DAO<User> {
	private static final String SELECT_USER_BY_ID = "SELECT * FROM USERS WHERE ID = :id";
	private static final String SELECT_USER_BY_NAME_AND_PASSWORD = 
				"SELECT * FROM USERS WHERE USER_NAME = :name AND PASSWORD = :password";
	
	public User findById(long id) {
		try (Session session = createSessionFactory().openSession();) {
			Query<User> query = session.createNativeQuery(SELECT_USER_BY_ID, User.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findById of User is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Not found User by ID");
		}
		return null;
	}
	
	public User save(User user) {
		return super.save(user);
	}
	
	public User delete(long id) {
		return super.delete(id);
	}
	
	public User update (User user) {
		return super.update(user);
	}
	
	public User findByNamePassword(User user) {
		try (Session session = createSessionFactory().openSession();) {
			Query<User> query = session.createNativeQuery(SELECT_USER_BY_NAME_AND_PASSWORD, User.class);
			query.setParameter("name", user.getUserName());
			query.setParameter("password", user.getPassword());
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findByNamePassword of User is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Not found User by Name and Password");
		}
		return null;
	}
}

