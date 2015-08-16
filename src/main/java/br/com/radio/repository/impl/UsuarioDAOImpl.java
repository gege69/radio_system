package br.com.radio.repository.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.radio.model.Usuario;
import br.com.radio.repository.UsuarioDAO;

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends AbstractDAO<Usuario, Long> implements UsuarioDAO {
	
	@Override
	public Boolean existsUsuarioByEmailOrLogin( String email, String login ) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteria = cb.createQuery( Long.class );
		
		Root<Usuario> root = criteria.from( Usuario.class );

		Predicate OR = cb.or( cb.equal( root.<String>get( "cd_email_usu" ), email ), 
							  cb.equal( root.<String>get( "cd_login_usu" ), login ) );

		criteria.select( cb.count( root ) );
		
		criteria.where( OR );

		Long qtd = em.createQuery(criteria).getSingleResult();
		
		return qtd > 0;
	}

}
