package disttx.generic.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

public class GenericRepository<T extends Serializable> {
	protected EntityManager em;
	private Class<T> clazz;
	{
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	public T findById(Object id) {
		return em.find(clazz, id);
	}
	public List<T> findAll(){
		return em.createQuery("from "+clazz.getName()).getResultList();
	}
	public void save(T entity) {
		em.persist(entity);
	}
}
