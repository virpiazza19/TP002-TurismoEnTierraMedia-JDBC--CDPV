package daos;

import java.util.List;

public interface GenericDAO<T> {

	public List<T> findAll();
	public int update(T t);
}