package rc.ac.singidunum.novisad.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rc.ac.singidunum.novisad.model.Zaposleni;

@Repository
public interface ZaposleniRepository extends JpaRepository<Zaposleni, Long> {
	public Optional<Zaposleni> findKorisnikByKorisnickoIme(String korisnickoIme);
	
	@Query("SELECT z FROM Zaposleni z WHERE " +
		       "(:ime IS NULL OR LOWER(z.ime) LIKE LOWER(CONCAT('%', :ime, '%'))) AND " +
		       "(:prezime IS NULL OR LOWER(z.prezime) LIKE LOWER(CONCAT('%', :prezime, '%'))) AND " +
		       "(:email IS NULL OR LOWER(z.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
		        
		       "(:odsekId IS NULL OR z.odsek.id = :odsekId)")
		List<Zaposleni> pretrazi(
		    @Param("ime") String ime,
		    @Param("prezime") String prezime,
		    @Param("email") String email,
		   
		    @Param("odsekId") Long odsekId
		);
	
	public Optional<Zaposleni> findById(Long id);
	


}
