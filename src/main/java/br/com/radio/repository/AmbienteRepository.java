package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.enumeration.StatusAmbiente;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Cliente;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

	Slice<Ambiente> findByClienteAndStatus( Pageable pageable, Cliente cliente, StatusAmbiente status );

	Page<Ambiente> findByCliente( Pageable pageable, Cliente cliente );

	Page<Ambiente> findByClienteAndNomeContainingIgnoreCase( Pageable pageable, Cliente cliente, String nome );
	
	List<Ambiente> findByCliente( Cliente cliente );

	List<Ambiente> findByClienteAndStatus( Cliente cliente, StatusAmbiente status );
	
	List<Ambiente> findByStatus( StatusAmbiente status );
	
	Long countByCliente( Cliente cliente );
	
	Long countByLogin( String login );
	
	Ambiente findByLogin( String login );
	
//	@Modifying
//    @Query( value = "update ambiente set ativo = ?1, set dataalteracao = clock_timestamp() where id_ambiente = ?2", nativeQuery = true )
//    int updateToggleAtivo( Boolean ativo, Long idAmbiente );


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
