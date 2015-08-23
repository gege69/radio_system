package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Ambiente;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

	
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
	
}
