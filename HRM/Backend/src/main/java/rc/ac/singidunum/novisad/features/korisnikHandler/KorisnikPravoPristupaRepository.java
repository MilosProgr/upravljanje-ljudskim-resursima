package rc.ac.singidunum.novisad.features.korisnikHandler;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikPravoPristupaRepository extends JpaRepository<KorisnikPravoPristupa, Long> {
	List<KorisnikPravoPristupa> findByZaposlenId(Long zaposlenId);

}
