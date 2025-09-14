package rc.ac.singidunum.novisad.features.odsustvo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdsustvoRepository extends JpaRepository<Odsustvo, Long> {
	List<Odsustvo> findByZaposleniId(Long id);
}
