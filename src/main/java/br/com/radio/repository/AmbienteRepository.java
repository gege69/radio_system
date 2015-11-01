package br.com.radio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Empresa;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

	Page<Ambiente> findByEmpresa( Pageable pageable, Empresa empresa );
	
	Long countByEmpresa( Empresa empresa );
	
	Long countByLogin( String login );
	
	/**
	 * 
	 * Exemplos
	 */
//	  @Query("SELECT t FROM Todo t where t.title = ?1 AND t.description = ?2")
//    public Optional<Todo> findByTitleAndDescription(String title, String description);
//     
//    @Query(value = "SELECT * FROM todos t where t.title = ?0 AND t.description = ?1", 
//        nativeQuery=true
//    )
//    public Optional<Todo> findByTitleAndDescription(String title, String description);
//	
//	  @Query("SELECT t FROM Todo t where t.title = :title AND t.description = :description")
//    public Optional<Todo> findByTitleAndDescription(@Param("title") String title, 
//                                                    @Param("description") String description);
//     
//    @Query(
//        value = "SELECT * FROM todos t where t.title = :title AND t.description = :description", 
//        nativeQuery=true
//    )
//    public Optional<Todo> findByTitleAndDescription(@Param("title") String title, 
//                                                    @Param("description") String description);
//	
//	@Query( value="select count(*) qtd from usuario where cd_email_usu = :email or cd_login_usu = :login", nativeQuery=true )
//	public Optional<BigInteger> countByEmailOrLogin( @Param("email") String email, @Param("login") String login);
//	

	
	
	/**
	 * Exemplo bacana pra construir o bean
	 */
//	@Query("select new com.foo.bar.entity.Document(d.docId, d.filename) from Document d where d.filterCol = ?1")
	
	
}
