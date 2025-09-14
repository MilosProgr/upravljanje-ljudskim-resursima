package rc.ac.singidunum.novisad.features.zaduzenje;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZaduzenjeRepository extends JpaRepository<Zaduzenje, Long> {
	List<Zaduzenje> findByZaposleniId(Long id);
}
