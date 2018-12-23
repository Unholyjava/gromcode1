package lessons_8;

public interface DAO<T>{
	T save(T entity) throws Exception;
	T delete(long id) throws Exception;
	T update (T entity) throws Exception;
	T findById(long id) throws Exception;
}
