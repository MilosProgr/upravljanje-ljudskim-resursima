package rc.ac.singidunum.novisad.features.obuka;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObukaRepository extends JpaRepository<Obuka, Long> {
	List<Obuka> findByZaposleniId(Long zaposleniId);
}
