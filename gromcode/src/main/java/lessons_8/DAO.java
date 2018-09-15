package lessons_8;

public interface DAO<T>{
	T save(T entity);
	T delete(long id);
	T update (T entity);
	T findById(long id);
}
