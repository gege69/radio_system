package br.com.radio.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.radio.model.Model;
import br.com.radio.repository.DAO;

@Repository
@Transactional
public abstract class AbstractDAO<T extends Model<ID>, ID extends Serializable> implements DAO<T, ID> {

	@SuppressWarnings("unchecked")
	protected Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public T save(T t) {
		
		if ( t.getId() == null )
			em.persist(t);
		else
			em.merge(t);
		
		em.flush();
//		em.refresh( t );
		
		return t;
	}

	@Override
	public T findById(ID id) {
		
		T t = (T) em.find(clazz, id);
		
		return t;
	}

	@Override
	public List<T> findAll() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<T> criteria = cb.createQuery(clazz);
		
		Root<T> root = criteria.from(clazz);
		
		criteria.select(root);
		
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public List<T> findAllWithOrderAsc( String field ) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<T> criteria = cb.createQuery(clazz);
		
		Root<T> root = criteria.from(clazz);
		
		criteria.select(root);
		criteria.orderBy( cb.asc( root.get( field ) ) );
		
		return em.createQuery(criteria).getResultList();
	}
	
	
	@Override
	public List<T> findAllWithOrderDesc( String field ) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<T> criteria = cb.createQuery(clazz);
		
		Root<T> root = criteria.from(clazz);
		
		criteria.select(root);
		criteria.orderBy( cb.desc( root.get( field ) ) );
		
		return em.createQuery(criteria).getResultList();
	}

	
	
//	@Override
//	public T findAllAtivos(String field, String hash)
//	{
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<T> criteria = builder.createQuery(clazz);
//		Root<T> obj = criteria.from(clazz);
//
//		criteria.where(builder.equal(obj.<String>get( field ), hash));
//		
//		return getSingleResult(em.createQuery(criteria));
//	}

	

	@Override
	public void remove(T t) {
		
		em.remove(t);
	}

	@Override
	public void removeById(ID id) {
		
		T t = this.findById(id);
		
		if ( t != null )
			this.remove(t);
	}
	
	@Override
	public T findByHash(String field, String hash)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> obj = criteria.from(clazz);

		criteria.where(builder.equal(obj.<String>get( field ), hash));
		
		return getSingleResult(em.createQuery(criteria));
	}

	
	public T getSingleResult(TypedQuery<T> query) 
	{
	    query.setMaxResults(1);
	    List<T> list = query.getResultList();
	    if (list == null || list.isEmpty()) {
	        return null;
	    }

	    return list.get(0);
	}
	
	
}
