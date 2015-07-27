package br.com.radio.repository;

import java.io.Serializable;
import java.util.List;

import br.com.radio.model.Model;

public interface DAO<T extends Model<ID>, ID extends Serializable> {

	T save(T t);
	
	T findById(ID id);
	
	List<T> findAll();
	
	List<T> findAll( String field, String value );
	
	T findLastResult( String field, String id, String value );
	
	List<T> findAllWithOrderAsc( String field );
	
	List<T> findAllWithOrderDesc( String field );
	
	void remove(T t);
	
	void removeById(ID id);
	
	T findByHash(String field, String hash);
	
}
